package com.example.onlinestore.dto;

import com.example.onlinestore.model.Product;

import java.util.List;

public record CategoryDTO(
        long id,
        String title,
        List<Product> products
) {
}
