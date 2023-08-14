package com.example.demo.repositories;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Product findProductByNameAndCategory(String name, Category category);

    Product findProductByName(String name);

    @Transactional
    int deleteProductByName(String name);
}
