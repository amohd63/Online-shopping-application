package com.example.demo;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;

@SpringBootApplication
@EnableEurekaClient
@RequiredArgsConstructor
//@ComponentScan(basePackages = {"com.example.demo.services", "com.example.demo.repositories"})
public class ProductServiceApplication {

//	private final ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

//	@Override
//	public void run(String... args) {
//		if (productRepository.count() < 1) {
//			Product product = new Product();
//			product.setName("iPhone 13");
//			product.setDescription("iPhone 13");
//			product.setPrice(BigDecimal.valueOf(1000));
//
//			productRepository.save(product);
//		}
//	}
}
