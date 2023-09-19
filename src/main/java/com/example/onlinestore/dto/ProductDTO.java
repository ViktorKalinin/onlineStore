package com.example.onlinestore.dto;

import com.example.onlinestore.model.Basket;
import com.example.onlinestore.model.Category;
import java.util.List;

public record ProductDTO(
        long id,
        List<Category> categories,
        List<Basket> baskets,
        String title,
        int amount,
        int price
) {
}
