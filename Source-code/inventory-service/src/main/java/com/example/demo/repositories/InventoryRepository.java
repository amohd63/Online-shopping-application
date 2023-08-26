package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Order, Long> {
    public Order findOrderByOrderNumber(String orderNumber);
    public int deleteOrderByOrderNumber(String orderNumber);
}