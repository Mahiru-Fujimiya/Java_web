package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import java.util.List;

public interface ProductService {

    List<ProductDTO> getAll();

    ProductDTO getById(Long id);

    ProductDTO create(ProductDTO dto);

    ProductDTO update(Long id, ProductDTO dto);

    String delete(Long id);

    // Nâng cao
    List<ProductDTO> search(String keyword);

    List<ProductDTO> getByCategory(Long categoryId);
}
