package com.example.onlinestore.controllers;

import com.example.onlinestore.dto.OrderDTO;
import com.example.onlinestore.dto.UserDTO;
import com.example.onlinestore.model.OrderStatus;
import com.example.onlinestore.services.OrderService;
import com.example.onlinestore.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO){
        OrderDTO order = orderService.save(orderDTO);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") long id){
        OrderDTO order = orderService.getOrderById(id);
        if(order != null){
            return ResponseEntity.ok(order);
        } return ResponseEntity.notFound().build();
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<OrderDTO>> getOrderByUser(@PathVariable("userId") long id){
        UserDTO user = userService.getById(id);
        List<OrderDTO> orders = orderService.getOrdersByUser(user);
        return ResponseEntity.ok(orders);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/byStatus/{id}")
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@PathVariable("status")OrderStatus status){
        List<OrderDTO> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable("id") long id, @RequestParam OrderStatus status){
        OrderDTO order = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
