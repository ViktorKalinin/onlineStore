package com.example.onlinestore.dto;

import com.example.onlinestore.model.Product;

import java.util.List;

public record BasketDTO(
        long id,
        UserDTO user,
        List<Product> products
) {

}
