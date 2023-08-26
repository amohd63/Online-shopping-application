package com.example.demo.services;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public String createProduct(ProductRequest productResponse) {
        if (productRepository.findProductByName(productResponse.getName()) != null) {
            return "Product [" + productResponse.getName() + "], is in the system!";
        }
        Product product = Product.builder()
                .name(productResponse.getName())
                .description(productResponse.getDescription())
                .price(productResponse.getPrice())
                .build();
        productRepository.save(product);
        return "Product added successfully";
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    public ProductResponse getProduct(String productName) {
        Product product = productRepository.findProductByName(productName);
        return mapToProductResponse(product);
    }

    public ProductResponse updateProduct(ProductRequest productRequest) {
        Product product = productRepository.findProductByName(productRequest.getName());
        if (product == null) {
            return null;
        }
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setName(productRequest.getName());
        productRepository.save(product);
        return mapToProductResponse(product);
    }

    public String deleteProduct(String productName) {
        return productRepository.deleteProductByName(productName) == 0 ? "Product is not found in the system" :
                "Product [" + productName + "] deleted successfully!";
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
