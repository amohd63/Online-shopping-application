package com.example.demo.services;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public String createProduct(ProductRequest productRequest) {
        if (productRepository.findProductByName(productRequest.getName()) != null) {
            return "Product [" + productRequest.getName() + "], is in the system!";
        }
        Product product = Product.builder()
                .skuCode(productRequest.getSkuCode())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
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

    public ProductResponse getProductBySkuCode(String skuCode) {
        Product product = productRepository.findProductBySkuCode(skuCode);
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
        product.setSkuCode(productRequest.getSkuCode());
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
                .skuCode(product.getSkuCode())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
