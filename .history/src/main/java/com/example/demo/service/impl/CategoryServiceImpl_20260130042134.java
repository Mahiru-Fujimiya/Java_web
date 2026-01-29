package com.example.demo.service.impl;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepo;
    private final CategoryMapper mapper;

    @Override
    public List<CategoryDTO> getAll() {
        return categoryRepo.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public CategoryDTO create(CategoryDTO dto) {
        Category category = mapper.toEntity(dto);
        categoryRepo.save(category);
        return mapper.toDTO(category);
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO dto) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy category"));

        category.setName(dto.getName());

        categoryRepo.save(category);

        return mapper.toDTO(category);
    }

    @Override
    public String delete(Long id) {
        if (!categoryRepo.existsById(id)) {
            throw new NotFoundException("Không tìm thấy category");
        }
        categoryRepo.deleteById(id);
        return "Xóa danh mục thành công";
    }
}
