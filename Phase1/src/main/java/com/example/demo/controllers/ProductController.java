package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService service;

    @DeleteMapping("/api/V1.0/product")
    public String deleteProduct(@RequestParam("name") String productName) {
        return service.deleteProduct(productName);
    }

    @GetMapping("/api/V1.0/product")
    public Product getProduct(@RequestParam("name") String productName, @RequestParam("category") String productCategory) {
        return service.getProduct(productName, productCategory);
    }
    
    @PostMapping("/api/V1.0/product")
    public String createProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }

    @GetMapping("/api/V1.0/product1")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @PutMapping("/api/V1.0/product")
    public Product updateProduct(@RequestBody Product product) {
        return service.updateProduct(product);
    }
}
