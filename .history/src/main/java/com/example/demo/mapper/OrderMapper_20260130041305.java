package com.example.demo.mapper;

import com.example.demo.dto.OrderItemDTO;
import com.example.demo.dto.OrderResponse;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderItemDTO toItemDTO(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        return dto;
    }

    public OrderResponse toDTO(Order order) {
        OrderResponse dto = new OrderResponse();

        dto.setOrderId(order.getId());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus());

        dto.setItems(
                order.getItems()
                        .stream()
                        .map(this::toItemDTO)
                        .collect(Collectors.toList())
        );

        return dto;
    }
}
