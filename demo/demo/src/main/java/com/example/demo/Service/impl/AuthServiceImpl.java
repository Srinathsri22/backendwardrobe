package com.example.demo.Service.impl;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User signup(String name, String mail, String password) {
        if (userRepository.findByEmail(mail).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(mail);
        user.setPassword(password);
        return userRepository.save(user);
    }

    @Override
    public UserDTO login(String mail, String password) {
        return userRepository.findByEmail(mail)
                .filter(user -> user.getPassword().equals(password))
                .map(user -> {
                    // Map User entity to UserDTO
                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(user.getId());
                    userDTO.setName(user.getName());
                    userDTO.setEmail(user.getEmail());
                    return userDTO;
                })
                .orElseThrow(() -> new RuntimeException("Invalid user or password"));
    }

}
