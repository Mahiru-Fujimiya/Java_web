package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Một cart có thể có nhiều cartItem
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    // Một sản phẩm có thể nằm trong nhiều cartItem
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
}
