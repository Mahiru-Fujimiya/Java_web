package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    // Vai trò: USER, ADMIN
    private String role = "USER";

    // Quan hệ 1–1 với Cart
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    // Quan hệ 1–N với Order
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
