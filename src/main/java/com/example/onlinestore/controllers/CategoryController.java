package com.example.onlinestore.controllers;

import com.example.onlinestore.dto.CategoryDTO;
import com.example.onlinestore.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO create = categoryService.saveCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") long id){
        CategoryDTO category = categoryService.getCategoryById(id);
        if(category != null){
            return ResponseEntity.ok(category);
        } return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
