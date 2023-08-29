package com.example.demo.repositories;

import com.example.demo.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    public Inventory findInventoryBySkuCode(String skuCode);
    public int deleteInventoryBySkuCode(String skuCode);
    public List<Inventory> findBySkuCodeIn(List<String> skuCode);
}