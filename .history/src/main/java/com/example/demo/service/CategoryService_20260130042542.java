package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAll();

    CategoryDTO create(CategoryDTO dto);

    CategoryDTO update(Long id, CategoryDTO dto);

    String delete(Long id);
}
