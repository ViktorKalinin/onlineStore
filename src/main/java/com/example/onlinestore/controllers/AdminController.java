package com.example.onlinestore.controllers;

import com.example.onlinestore.dto.UserDTO;
import com.example.onlinestore.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO createUser = userService.save(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long id){
        UserDTO user = userService.getById(id);
        if(user != null){
            return ResponseEntity.ok(user);
        } return ResponseEntity.notFound().build();
    }

    @GetMapping("/username/{userName}")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable String userName){
        UserDTO user = userService.getByUserName(userName);
        if(user != null){
            return ResponseEntity.ok(user);
        } return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
