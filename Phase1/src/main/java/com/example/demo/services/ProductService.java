package com.example.demo.services;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public String createProduct(Product product) {
        if (productRepository.findProductByName(product.getName()) != null) {
            return "Product [" + product.getName() + "], is in the system!";
        }
        try{
            Category.valueOf(product.getCategory());
        }catch (IllegalArgumentException e){
            return "Category [" + product.getCategory() + "] is not in Enum";
        }
        if (product.getPrice().compareTo(new BigDecimal(0)) <= 0 || product.getPrice().compareTo(new BigDecimal(100000)) > 0) {
            return "Product price [" + product.getPrice() + "] is not acceptable, range [0 - 100,000]!";
        }
        productRepository.save(product);
        return "Product added successfully";
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(String productName, String productCategory) {
        return productRepository.findProductByNameAndCategory(productName, Category.valueOf(productCategory));
    }

    public Product updateProduct(Product product) {
        Product product1 = productRepository.findProductByName(product.getName());
        return productRepository.save(product);
    }

    public String deleteProduct(String productName) {
        return productRepository.deleteProductByName(productName) == 0 ? "Product is not found in the system" :
                "Product [" + productName + "] deleted successfully!";
    }
}
