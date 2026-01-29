package com.example.demo.service.impl;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final ProductMapper mapper;

    @Override
    public List<ProductDTO> getAll() {
        return productRepo.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public ProductDTO getById(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm"));

        return mapper.toDTO(product);
    }

    @Override
    public ProductDTO create(ProductDTO dto) {
        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy category"));

        Product product = mapper.toEntity(dto, category);

        productRepo.save(product);

        return mapper.toDTO(product);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO dto) {

        Product product = productRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm"));

        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy category"));

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setDescription(dto.getDescription());
        product.setImageUrl(dto.getImageUrl());
        product.setCategory(category);

        productRepo.save(product);

        return mapper.toDTO(product);
    }

    @Override
    public String delete(Long id) {
        if (!productRepo.existsById(id)) {
            throw new NotFoundException("Không tìm thấy sản phẩm");
        }

        productRepo.deleteById(id);
        return "Xóa sản phẩm thành công";
    }

    // ===============================
    //   TÍNH NĂNG NÂNG CAO (OPTION)
    // ===============================

    @Override
    public List<ProductDTO> search(String keyword) {
        return productRepo.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public List<ProductDTO> getByCategory(Long categoryId) {
        return productRepo.findByCategoryId(categoryId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
}
