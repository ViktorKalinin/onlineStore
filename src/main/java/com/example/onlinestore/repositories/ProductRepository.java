package com.example.onlinestore.repositories;

import com.example.onlinestore.dto.CategoryDTO;
import com.example.onlinestore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitleContaining(String title);

    List<Product> findByCategoriesContains(CategoryDTO category);

    List<Product> findByPriceLessThan(int price);

    List<Product> findByPriceBetween(int minPrice,int maxPrice);

    List<Product> findAllByOrderByPriceAsc();

    List<Product> findAllByOrderByPriceDesc();
}
