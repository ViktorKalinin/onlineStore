package com.example.onlinestore.services.impl;

import com.example.onlinestore.dto.CategoryDTO;
import com.example.onlinestore.model.Category;
import com.example.onlinestore.repositories.CategoryRepository;
import com.example.onlinestore.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void deleteCategory(long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Категория не найдена"));
        repository.delete(category);
    }

    @Override
    public CategoryDTO getCategoryById(long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Категория не найдена"));
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = repository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categoryList = repository.findAll();
        return
                categoryList.stream()
                        .map(category -> modelMapper.map(category, CategoryDTO.class))
                        .collect(Collectors.toList());
    }
}
