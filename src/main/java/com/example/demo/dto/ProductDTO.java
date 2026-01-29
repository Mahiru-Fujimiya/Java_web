package com.example.demo.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private int quantity;
    private String description;
    private String imageUrl;
    private Long categoryId;
}
