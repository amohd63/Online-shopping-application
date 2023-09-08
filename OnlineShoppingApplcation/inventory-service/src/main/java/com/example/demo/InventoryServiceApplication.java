package com.example.demo;

import com.example.demo.models.Inventory;
import com.example.demo.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@SpringBootApplication
@EnableEurekaClient
@RequiredArgsConstructor
public class InventoryServiceApplication implements CommandLineRunner {

	private final InventoryRepository inventoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

		@Override
	public void run(String... args) {
		if (inventoryRepository.count() < 1) {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("SKU001");
			inventory.setQuantity(1000);
			inventoryRepository.save(inventory);
		}
	}
}
