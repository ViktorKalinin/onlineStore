package com.example.onlinestore.services;

import com.example.onlinestore.dto.BasketDTO;
import com.example.onlinestore.dto.UserDTO;
import java.util.List;

public interface BasketService {
    void deleteBasket(long id);

    BasketDTO save(BasketDTO basketDTO);

    BasketDTO getBasketById(long id);

    List<BasketDTO> getAllBaskets();

    BasketDTO getBasketByUser(UserDTO userDTO);

    int countProductsInBasket(long basketId);
}
