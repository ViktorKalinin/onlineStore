package com.example.onlinestore.dto;

import com.example.onlinestore.model.OrderStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private long id;
    private LocalDateTime creationTime;
    private UserDTO user;
    private OrderStatus orderStatus;
    private int cost;
}
