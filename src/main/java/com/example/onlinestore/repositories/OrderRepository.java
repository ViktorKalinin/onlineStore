package com.example.onlinestore.repositories;

import com.example.onlinestore.model.Order;
import com.example.onlinestore.model.OrderStatus;
import com.example.onlinestore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUser(User user);

    List<Order> findAllByStatus(OrderStatus orderStatus);
}
