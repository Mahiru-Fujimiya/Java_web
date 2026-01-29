package com.example.demo.controller;

import com.example.demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // Lấy giỏ hàng của user hiện tại
    @GetMapping
    public ResponseEntity<?> getCart() {
        return ResponseEntity.ok(cartService.getCart());
    }

    // Thêm sản phẩm vào giỏ
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        return ResponseEntity.ok(cartService.addProduct(productId, quantity));
    }

    // Xoá sản phẩm khỏi giỏ
    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<?> removeProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(cartService.removeProduct(productId));
    }
}
