package com.example.onlinestore.services;


import com.example.onlinestore.dto.CategoryDTO;
import com.example.onlinestore.dto.ProductDTO;
import com.example.onlinestore.model.Category;

import java.util.List;

public interface ProductService {

    void deleteProduct(long id);

    ProductDTO save(ProductDTO productDTO);

    ProductDTO getProductById(long id);

    List<ProductDTO> getAllProducts();

    List<ProductDTO> getProductsByTitle(String title);

    List<ProductDTO> getProductsByCategory(CategoryDTO category);

    List<ProductDTO> getProductsByPriceLessThan(int price);

    List<ProductDTO> getProductsByPriceRange(int minPrice, int maxPrice);

    List<ProductDTO> getAllByPriceAsc();

    List<ProductDTO> getAllByPriceDesc();

}
