package com.example.onlinestore.services;

import com.example.onlinestore.dto.OrderDTO;
import com.example.onlinestore.dto.UserDTO;
import com.example.onlinestore.model.OrderStatus;

import java.util.List;

public interface OrderService {

    void deleteOrder(long id);

    OrderDTO save(OrderDTO orderDTO);

    OrderDTO getOrderById(long id);

    List<OrderDTO> getOrdersByUser(UserDTO userDTO);

    List<OrderDTO> getAllOrders();

    List<OrderDTO> getOrdersByStatus(OrderStatus orderStatus);

    OrderDTO updateOrderStatus(long id, OrderStatus status);
}
