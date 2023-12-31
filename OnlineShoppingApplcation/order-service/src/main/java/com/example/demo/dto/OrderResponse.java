package com.example.demo.dto;

import com.example.demo.models.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private long id;
    private String orderNumber;
    private List<OrderLineItems> orderLineItemsList;
}