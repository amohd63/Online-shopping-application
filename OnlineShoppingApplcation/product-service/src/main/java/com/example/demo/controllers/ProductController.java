package com.example.demo.controllers;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.models.Product;
import com.example.demo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return service.getAllProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createProduct(@RequestBody ProductRequest product) {
        return service.createProduct(product);
    }

    @GetMapping(params = "name")
    @ResponseStatus(HttpStatus.FOUND)
    public ProductResponse getProduct(@RequestParam(value = "name", required = true) String productName) {
        return service.getProduct(productName);
    }

    @GetMapping(params = "skuCode")
    @ResponseStatus(HttpStatus.FOUND)
    public ProductResponse getProductBySkuCode(@RequestParam(value = "skuCode", required = true) String skuCode) {
        return service.getProductBySkuCode(skuCode);
    }

    @DeleteMapping(params = "name")
    @ResponseStatus(HttpStatus.OK)
    public String deleteProduct(@RequestParam String name) {
        return service.deleteProduct(name);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProductResponse updateProduct(@RequestBody ProductRequest product) {
        return service.updateProduct(product);
    }
}