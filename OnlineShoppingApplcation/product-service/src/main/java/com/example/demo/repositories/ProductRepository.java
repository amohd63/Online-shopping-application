package com.example.demo.repositories;

import com.example.demo.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product findProductByName(String name);

    Product findProductBySkuCode(String skuCode);

    int deleteProductByName(String name);
}
