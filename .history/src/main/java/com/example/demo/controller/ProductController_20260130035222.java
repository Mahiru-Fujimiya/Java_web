package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // Lấy toàn bộ sản phẩm
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    // Lấy chi tiết 1 sản phẩm theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    // Tạo mới sản phẩm
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDTO request) {
        return ResponseEntity.ok(productService.create(request));
    }

    // Cập nhật sản phẩm
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductDTO request) {
        return ResponseEntity.ok(productService.update(id, request));
    }

    // Xóa sản phẩm
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(productService.delete(id));
    }
}
