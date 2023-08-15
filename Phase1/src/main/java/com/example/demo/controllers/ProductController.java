package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1.0/product")
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @DeleteMapping(params = "name")
    public String deleteProduct(@RequestParam String name) {
        return service.deleteProduct(name);
    }

    @GetMapping(params = {"name", "category"})
    public Product getProduct(@RequestParam(value = "name", required = true) String productName, @RequestParam(value = "category", required = true) String productCategory) {
        return service.getProduct(productName, productCategory);
    }

    @PostMapping
    public String createProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return service.updateProduct(product);
    }
}