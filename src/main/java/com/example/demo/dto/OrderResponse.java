package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private Long orderId;
    private LocalDateTime createdAt;
    private double totalPrice;
    private String status;
    private List<OrderItemDTO> items;
}
