package com.example.demo.controller;

import com.example.demo.dto.OrderRequest;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // Tạo đơn hàng
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    // Lấy danh sách đơn hàng của user hiện tại
    @GetMapping
    public ResponseEntity<?> getMyOrders() {
        return ResponseEntity.ok(orderService.getOrdersOfUser());
    }

    // Lấy chi tiết đơn hàng
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }
}
