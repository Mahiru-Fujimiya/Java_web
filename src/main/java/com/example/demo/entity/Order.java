package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Người tạo đơn
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Thời gian tạo đơn
    private LocalDateTime createdAt = LocalDateTime.now();

    // Tổng tiền đơn hàng
    private double totalPrice;

    // Trạng thái đơn hàng
    // PENDING, PAID, SHIPPING, COMPLETED
    private String status = "PENDING";

    // Thông tin giao hàng
    private String address;
    private String phoneNumber;

    // Các sản phẩm trong đơn
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;
}
