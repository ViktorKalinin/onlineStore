package com.example.onlinestore.dto;
import com.example.onlinestore.model.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private long id;
    private String userName;
    private String email;
    private Role role;
}

