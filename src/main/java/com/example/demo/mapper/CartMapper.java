package com.example.demo.mapper;

import com.example.demo.dto.CartItemDTO;
import com.example.demo.dto.CartResponse;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartMapper {

    public CartItemDTO toItemDTO(CartItem item) {
        CartItemDTO dto = new CartItemDTO();
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setPrice(item.getProduct().getPrice());
        dto.setQuantity(item.getQuantity());
        return dto;
    }

    public CartResponse toCartResponse(Cart cart) {
        CartResponse response = new CartResponse();

        response.setItems(
                cart.getItems()
                        .stream()
                        .map(this::toItemDTO)
                        .collect(Collectors.toList())
        );

        double total = cart.getItems()
                .stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum();

        response.setTotalPrice(total);

        return response;
    }
}
