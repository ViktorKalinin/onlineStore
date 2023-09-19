package com.example.onlinestore.services.impl;
import com.example.onlinestore.dto.UserDTO;
import com.example.onlinestore.model.User;
import com.example.onlinestore.repositories.UserRepository;
import com.example.onlinestore.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User savedUser = repository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO getById(long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с таким id не найден"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO getByUserName(String userName) {
        User user = repository.findByUserName(userName);
        if(user == null){
           throw new EntityNotFoundException("Пользователья с таким именем не найден");
       }
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void deleteUser(long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с таким id не найден"));
        repository.delete(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userList = repository.findAll();
        return
                userList.stream()
                        .map(user -> modelMapper.map(user, UserDTO.class))
                        .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User userToUpdate = modelMapper.map(userDTO, User.class);
        User updatedUser = repository.save(userToUpdate);
        return modelMapper.map(updatedUser, UserDTO.class);
    }
}