package com.example.onlinestore.services;

import com.example.onlinestore.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    void deleteCategory(long id);

    CategoryDTO getCategoryById(long id);

    CategoryDTO saveCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategories();
}
