package com.example.onlinestore.controllers;

import com.example.onlinestore.dto.BasketDTO;
import com.example.onlinestore.dto.UserDTO;
import com.example.onlinestore.services.BasketService;
import com.example.onlinestore.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/basket")
public class BasketController {
    private final BasketService basketService;
    private final UserService userService;

    public BasketController(BasketService basketService, UserService userService) {
        this.basketService = basketService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<BasketDTO> createBasket(@RequestBody BasketDTO basketDTO){
        BasketDTO basket = basketService.save(basketDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(basket);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasketDTO> getBasketById(@PathVariable("id") long id){
        BasketDTO basket = basketService.getBasketById(id);
        if(basket != null){
            return ResponseEntity.ok(basket);
        } return ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<BasketDTO> getBasketByUser(@PathVariable("userId") long id){
        UserDTO user = userService.getById(id);
        BasketDTO basket = basketService.getBasketByUser(user);
        return ResponseEntity.ok(basket);
    }

    @GetMapping
    public ResponseEntity<List<BasketDTO>> getAllBaskets(){
        List<BasketDTO> baskets = basketService.getAllBaskets();
        return ResponseEntity.ok(baskets);
    }

    @GetMapping("/count/{id}")
    public ResponseEntity<Integer> countProducts(@PathVariable("id") long id){
        int count = basketService.countProductsInBasket(id);
        return ResponseEntity.ok(count);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBasket(@PathVariable("id") long id){
        basketService.deleteBasket(id);
        return ResponseEntity.noContent().build();
    }
}
