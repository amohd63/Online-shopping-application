package com.example.demo.controllers;

import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;
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
    public List<OrderResponse> getAllOrders() {
        return service.getAllOrders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest product) {
        return service.placeOrder(product);
    }

    @GetMapping(params = "orderNumber")
    @ResponseStatus(HttpStatus.FOUND)
    public OrderResponse getOrder(@RequestParam(value = "orderNumber", required = true) String orderNumber) {
        return service.getOrder(orderNumber);
    }

    @DeleteMapping(params = "orderNumber")
    @ResponseStatus(HttpStatus.OK)
    public String deleteOrder(@RequestParam String orderNumber) {
        return service.deleteProduct(orderNumber);
    }
}