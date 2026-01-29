package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartResponse {
    private List<CartItemDTO> items;
    private double totalPrice;
}
