package com.example.onlinestore.services.impl;

import com.example.onlinestore.dto.OrderDTO;
import com.example.onlinestore.dto.UserDTO;
import com.example.onlinestore.model.Order;
import com.example.onlinestore.model.OrderStatus;
import com.example.onlinestore.model.User;
import com.example.onlinestore.repositories.OrderRepository;
import com.example.onlinestore.services.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void deleteOrder(long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Данный заказ не найден"));
        repository.delete(order);
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        Order savedOrder = repository.save(order);
        return modelMapper.map(savedOrder, OrderDTO.class);
    }

    @Override
    public OrderDTO getOrderById(long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Данный заказ не найден"));
        return modelMapper.map(order, OrderDTO.class);
    }

    @Override
    public List<OrderDTO> getOrdersByUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        List<Order> userOrders = repository.findAllByUser(user);

        return userOrders.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orderList = repository.findAll();
        return
                orderList.stream()
                        .map(order -> modelMapper.map(order, OrderDTO.class))
                        .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByStatus(OrderStatus orderStatus) {
        List<Order> orderList = repository.findAllByStatus(orderStatus);
        return
                orderList.stream()
                        .map(order -> modelMapper.map(order, OrderDTO.class))
                        .collect(Collectors.toList());
    }

    @Override
    public OrderDTO updateOrderStatus(long id, OrderStatus status) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Заказ не найден"));
        order.setStatus(status);
        Order updatedOder = repository.save(order);
        return modelMapper.map(updatedOder, OrderDTO.class);
    }
}
