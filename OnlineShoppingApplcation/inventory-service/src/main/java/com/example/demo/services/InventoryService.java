package com.example.demo.services;

import com.example.demo.dto.InventoryRequest;
import com.example.demo.dto.InventoryResponse;
import com.example.demo.dto.ProductResponse;
import com.example.demo.models.Inventory;
import com.example.demo.repositories.InventoryRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private WebClient webClient;

    public Boolean placeProduct(InventoryRequest inventoryRequest) {
        ProductResponse productResponse = webClient.get()
                .uri("http://localhost:8080/api/product",
                        uriBuilder -> uriBuilder.queryParam("skuCode", inventoryRequest.getSkuCode()).build())
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();
        if (productResponse == null) {
            return false;
        }
        Inventory inventory = new Inventory();
        inventory.setSkuCode(inventoryRequest.getSkuCode());
        inventory.setQuantity(inventoryRequest.getQuantity());
        inventoryRepository.save(inventory);
        return true;
    }

    public List<InventoryResponse> getALlInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventories.stream().map(this::mapToInventoryResponse).collect(Collectors.toList());
    }

    public InventoryResponse getProductFromInventory(String skuCode) {
        Inventory inventory = inventoryRepository.findInventoryBySkuCode(skuCode);
        return mapToInventoryResponse(inventory);
    }

    public String deleteProductFromInventory(String skuCode) {
        return inventoryRepository.deleteInventoryBySkuCode(skuCode) == 0 ? "Product is not found in the inventory" :
                "Product [" + skuCode + "] deleted successfully from inventory!";
    }

    private InventoryResponse mapToInventoryResponse(Inventory inventory) {
        return InventoryResponse.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity() > 0)
                .build();
    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }
}
