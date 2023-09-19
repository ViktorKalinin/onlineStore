package com.example.onlinestore.services;


import com.example.onlinestore.dto.UserDTO;
import java.util.List;

public interface UserService{

    UserDTO save(UserDTO userDTO);

    UserDTO getById(long id);

    UserDTO getByUserName(String userName);

    void deleteUser(long id);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(UserDTO userDTO);
}
