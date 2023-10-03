package com.example.onlinestore.services.impl;

import com.example.onlinestore.dto.UserDTO;
import com.example.onlinestore.model.Role;
import com.example.onlinestore.model.User;
import com.example.onlinestore.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceImplTest {

    @Spy
    private ModelMapper modelMapper;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        userServiceImpl = new UserServiceImpl(userRepository, modelMapper);
    }
    @Test
    void testAddUser() {
        UserDTO userDTO = new UserDTO("Nikita", "test23@mail.ru", Role.USER);
        User user = modelMapper.map(userDTO, User.class);

        doReturn(user).when(modelMapper).map(userDTO, User.class);
        when(userRepository.save(user)).thenReturn(user);

        UserDTO savedUser = userServiceImpl.save(userDTO);

        assertNotNull(savedUser);
        assertEquals(userDTO.getUserName(), savedUser.getUserName());
        verify(userRepository, times(1)).save(user);
        verify(modelMapper, times(1)).map(user, UserDTO.class);
    }

    @Test
    void testGetByIdWhenUserFound() {
        long id = 105;
        UserDTO userDTO = new UserDTO(id,"Nikita", "test23@mail.ru", Role.USER);
        User user = modelMapper.map(userDTO, User.class);

        doReturn(user).when(modelMapper).map(userDTO, User.class);
        when(userRepository.save(user)).thenReturn(user);

        UserDTO savedUser = userServiceImpl.save(userDTO);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        UserDTO userDTOGetId = userServiceImpl.getById(id);

        assertEquals(savedUser.getId(), userDTOGetId.getId());
        verify(userRepository, times(1)).findById(id);
        verify(modelMapper, times(2)).map(user, UserDTO.class);
    }

    @Test
    void testGetByIdWhenUserNotFound() {
        when(userRepository.findById(1000L)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            userServiceImpl.getById(1000L);
        });
        verify(userRepository, times(1)).findById(1000L);
    }


    @Test
    void testGetByUserNameWhenUserFound() {
        String userName = "Nikita";
        UserDTO userDTO = new UserDTO(userName, "test23@mail.ru", Role.USER);
        User user = modelMapper.map(userDTO, User.class);

        doReturn(user).when(modelMapper).map(userDTO, User.class);
        when(userRepository.save(user)).thenReturn(user);

        UserDTO savedUser = userServiceImpl.save(userDTO);

        when(userRepository.findByUserName(userName)).thenReturn(user);

        UserDTO userDTOGetByName = userServiceImpl.getByUserName(userName);

        assertEquals(savedUser.getUserName(), userDTOGetByName.getUserName());
        verify(userRepository, times(1)).findByUserName(userName);
        verify(modelMapper, times(2)).map(user, UserDTO.class);
    }

    @Test
    void testGetByUserNameWhenUserNotFound() {
        when(userRepository.findByUserName("Igor")).thenReturn(null);
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            userServiceImpl.getByUserName("Igor");
        });
        verify(userRepository, times(1)).findByUserName("Igor");
    }

    @Test
    void testGetAllUsers() {
        List<User> userList = new ArrayList<>(){
            {add(new User("Nikita", "test23@mail.ru", Role.USER));
                add(new User("Igor", "testAdmin@mail.ru", Role.ADMIN));
            }};

        when(userRepository.findAll()).thenReturn(userList);
        doAnswer(invocation -> {
            User user = invocation.getArgument(0);
            return new UserDTO(user.getUserName(), user.getEmail(), user.getRole());
        }).when(modelMapper).map(any(User.class), eq(UserDTO.class));

        List<UserDTO> userDTOList = userServiceImpl.getAllUsers();

        assertEquals(userList.size(), userDTOList.size());
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            UserDTO userDTO = userDTOList.get(i);

            assertEquals(user.getUserName(), userDTO.getUserName());
            assertEquals(user.getEmail(), userDTO.getEmail());
            assertEquals(user.getRole(), userDTO.getRole());
        }
    }

    @Test
    void testUpdateUser() {
        UserDTO userDTO = new UserDTO("Nikita", "test23@mail.ru", Role.USER);
        User user = modelMapper.map(userDTO, User.class);

        doReturn(user).when(modelMapper).map(userDTO, User.class);
        when(userRepository.save(user)).thenReturn(user);

        UserDTO updateUser = userServiceImpl.updateUser(userDTO);

        assertEquals(userDTO.getUserName(), updateUser.getUserName());
        verify(userRepository, times(1)).save(user);
        verify(modelMapper, times(1)).map(user, UserDTO.class);
    }

    @Test
    void testDeleteUserWhenFind() {
        long id = 105;
        User user = new User(id, "Nikita", "test23@mail.ru", Role.USER);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        userServiceImpl.deleteUser(id);

        verify(userRepository, atLeastOnce()).findById(id);
        verify(userRepository, atLeastOnce()).delete(user);
    }

}