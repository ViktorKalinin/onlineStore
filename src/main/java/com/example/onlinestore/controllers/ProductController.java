package com.example.onlinestore.controllers;

import com.example.onlinestore.dto.CategoryDTO;
import com.example.onlinestore.dto.ProductDTO;
import com.example.onlinestore.services.CategoryService;
import com.example.onlinestore.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        ProductDTO product = productService.save(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") long id){
        ProductDTO product = productService.getProductById(id);
        if(product != null){
            return ResponseEntity.ok(product);
        } return ResponseEntity.notFound().build();
    }

    @GetMapping("/byTitle/{title}")
    public ResponseEntity<List<ProductDTO>> getProductsByTitle(@PathVariable("title") String title){
        List<ProductDTO> products = productService.getProductsByTitle(title);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/byCategory/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable("categoryId") long categoryId){
        CategoryDTO category = categoryService.getCategoryById(categoryId);
        List<ProductDTO> products =productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/byLessThan/{price}")
    public ResponseEntity<List<ProductDTO>> getProductsByPriceLessThan(@PathVariable("price") int price){
        List<ProductDTO> products = productService.getProductsByPriceLessThan(price);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/ByPriceRange")
    public ResponseEntity<List<ProductDTO>> getProductsByPriceRange(@RequestParam("minPrice") int minPrice, @RequestParam("maxPrice") int maxPrice){
        List<ProductDTO> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/byPriceAsc")
    public ResponseEntity<List<ProductDTO>> getAllByPriceAsc() {
        List<ProductDTO> products = productService.getAllByPriceAsc();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/byPriceDesc")
    public ResponseEntity<List<ProductDTO>> getAllByPriceDesc() {
        List<ProductDTO> products = productService.getAllByPriceDesc();
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
