package com.example.demo.controllers;

import com.example.demo.dto.InventoryRequest;
import com.example.demo.dto.InventoryResponse;
import com.example.demo.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    InventoryService service;

    @GetMapping
    public List<InventoryResponse> getAllInventories() {
        return service.getALlInventories();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeProduct(@RequestBody InventoryRequest inventoryRequest) {
        return service.placeProduct(inventoryRequest);
    }

    @GetMapping(params = "skuCode")
    @ResponseStatus(HttpStatus.FOUND)
    public InventoryResponse getProductFromInventory(@RequestParam(value = "skuCode", required = true) String skuCode) {
        return service.getProductFromInventory(skuCode);
    }

    @DeleteMapping(params = "skuCode")
    @ResponseStatus(HttpStatus.OK)
    public String deleteProductFromInventory(@RequestParam String skuCode) {
        return service.deleteProductFromInventory(skuCode);
    }
}