package com.example.onlinestore.repositories;

import com.example.onlinestore.model.Role;
import com.example.onlinestore.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUserName() {
        User user = new User("Ruslan", "test2@mail.ru", Role.ADMIN);
        userRepository.save(user);
        User savedUSer = userRepository.findByUserName("Ruslan");
        assertEquals(user.getUserName(), savedUSer.getUserName());
    }
}