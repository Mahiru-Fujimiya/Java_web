package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private double price;

    private int quantity;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imageUrl;

    // Nhiều sản phẩm thuộc 1 category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Một sản phẩm có thể xuất hiện trong nhiều cart item
    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

    // Một sản phẩm có thể xuất hiện trong nhiều order item
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;
}
