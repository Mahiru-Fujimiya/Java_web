package com.example.demo.service;

import com.example.demo.dto.CartResponse;

public interface CartService {

    CartResponse getCart();

    CartResponse addProduct(Long productId, int quantity);

    CartResponse removeProduct(Long productId);
}
