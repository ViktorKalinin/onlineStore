package com.example.onlinestore.dto;
import com.example.onlinestore.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long id;
    private String userName;
    private String email;
    private Role role;

    public UserDTO(String userName, String email, Role role) {
        this.userName = userName;
        this.email = email;
        this.role = role;
    }

}

