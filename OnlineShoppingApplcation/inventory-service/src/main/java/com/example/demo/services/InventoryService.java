package com.example.demo.services;

import com.example.demo.dto.InventoryRequest;
import com.example.demo.dto.InventoryResponse;
import com.example.demo.models.Inventory;
import com.example.demo.repositories.InventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public String placeProduct(InventoryRequest inventoryRequest) {
        Inventory inventory = new Inventory();
        inventory.setSkuCode(inventoryRequest.getSkuCode());
        inventory.setQuantity(inventoryRequest.getQuantity());
        inventoryRepository.save(inventory);
        return "Product placed successfully in the inventory";
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
                .quantity(inventory.getQuantity())
                .build();
    }
}
