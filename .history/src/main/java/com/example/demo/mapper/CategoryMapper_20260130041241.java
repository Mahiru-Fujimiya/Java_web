package com.example.demo.mapper;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    public Category toEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setId(dto.getId());   // dùng khi update
        category.setName(dto.getName());
        return category;
    }
}
