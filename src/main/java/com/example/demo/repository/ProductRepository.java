package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Tìm theo category
    List<Product> findByCategoryId(Long categoryId);

    // Tìm kiếm theo tên (LIKE)
    List<Product> findByNameContainingIgnoreCase(String name);
}
