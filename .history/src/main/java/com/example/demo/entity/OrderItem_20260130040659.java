package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Một đơn hàng có nhiều OrderItem
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // Một sản phẩm có thể nằm trong nhiều OrderItem
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    // Giá của sản phẩm tại thời điểm mua
    private double price;
}
