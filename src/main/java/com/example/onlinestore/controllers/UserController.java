package com.example.onlinestore.controllers;

import com.example.onlinestore.dto.UserDTO;
import com.example.onlinestore.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO createUser = userService.save(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") long id, @RequestBody UserDTO userDTO){
        userDTO.setId(id);
        UserDTO user = userService.updateUser(userDTO);
        return ResponseEntity.ok(user);
    }

}
