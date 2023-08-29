package com.example.demo.controllers;

import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService service;

    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return service.getAllOrders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "placeOrder", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "placeOrder")
    @Retry(name = "placeOrder")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        return CompletableFuture.supplyAsync(() -> service.placeOrder(orderRequest));
    }

    @GetMapping(params = "orderNumber")
    @ResponseStatus(HttpStatus.FOUND)
    public OrderResponse getOrder(@RequestParam(value = "orderNumber", required = true) String orderNumber) {
        return service.getOrder(orderNumber);
    }

    @DeleteMapping(params = "orderNumber")
    @ResponseStatus(HttpStatus.OK)
    public String deleteOrder(@RequestParam String orderNumber) {
        return service.deleteProduct(orderNumber);
    }

    public CompletableFuture<String> fallbackMethod(RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please try again later!");
    }
}