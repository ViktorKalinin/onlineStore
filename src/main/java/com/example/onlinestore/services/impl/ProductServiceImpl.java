package com.example.onlinestore.services.impl;

import com.example.onlinestore.dto.CategoryDTO;
import com.example.onlinestore.dto.ProductDTO;
import com.example.onlinestore.model.Category;
import com.example.onlinestore.model.Product;
import com.example.onlinestore.repositories.ProductRepository;
import com.example.onlinestore.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void deleteProduct(long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Продукт не найден"));
        repository.delete(product);
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product savedProduct = repository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO getProductById(long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Продукт не найден"));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> productList = repository.findAll();
        return
                productList.stream()
                        .map(product -> modelMapper.map(product, ProductDTO.class))
                        .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsByTitle(String title) {
        List<Product> productList = repository.findByTitleContaining(title);
        return productList.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsByCategory(CategoryDTO category) {
        List<Product> productList = repository.findByCategoriesContains(category);
        return productList.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsByPriceLessThan(int price) {
        List<Product> productList = repository.findByPriceLessThan(price);
        return productList.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsByPriceRange(int minPrice, int maxPrice) {
        List<Product> productList = repository.findByPriceBetween(minPrice, maxPrice);
        return productList.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllByPriceAsc() {
        List<Product> productList = repository.findAllByOrderByPriceAsc();
        return productList.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllByPriceDesc() {
        List<Product> productList = repository.findAllByOrderByPriceDesc();
        return productList.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }
}
