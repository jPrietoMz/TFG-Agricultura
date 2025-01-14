package com.tfg.agricultura.agricultura_backend.service;


import com.tfg.agricultura.agricultura_backend.dto.UserDTO;
import com.tfg.agricultura.agricultura_backend.model.User;
import com.tfg.agricultura.agricultura_backend.repository.UserRepository;
import com.tfg.agricultura.dto.UserDTO;
import com.tfg.agricultura.model.User;
import com.tfg.agricultura.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());
        userRepository.save(user);
    }
}