package com.example.onlinestore.services.impl;

import com.example.onlinestore.dto.BasketDTO;
import com.example.onlinestore.dto.UserDTO;
import com.example.onlinestore.model.Basket;
import com.example.onlinestore.model.User;
import com.example.onlinestore.repositories.BasketRepository;
import com.example.onlinestore.services.BasketService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BasketServiceImpl implements BasketService {
    private final BasketRepository repository;
    private final ModelMapper modelMapper;

    public BasketServiceImpl(BasketRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void deleteBasket(long id) {
        Basket basket = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Корзина не найдена"));
        repository.delete(basket);
    }

    @Override
    public BasketDTO save(BasketDTO basketDTO) {
        Basket basket = modelMapper.map(basketDTO, Basket.class);
        Basket savedBasket = repository.save(basket);
        return modelMapper.map(savedBasket, BasketDTO.class);
    }

    @Override
    public BasketDTO getBasketById(long id) {
        Basket basket = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Корзина не найдена"));
        return modelMapper.map(basket, BasketDTO.class);
    }

    @Override
    public List<BasketDTO> getAllBaskets() {
        List<Basket> basketList = repository.findAll();
        return
            basketList.stream()
                    .map(basket -> modelMapper.map(basket, BasketDTO.class))
                    .collect(Collectors.toList());
    }

    @Override
    public BasketDTO getBasketByUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        Basket basket = repository.findByUser(user);
        return modelMapper.map(basket, BasketDTO.class);
    }

    @Override
    public int countProductsInBasket(long basketId) {
        return repository.countProductsInBasket(basketId);
    }
}
