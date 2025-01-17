package com.tfg.agricultura.agricultura_backend.service;

import com.tfg.agricultura.agricultura_backend.dto.UserDTO;
import com.tfg.agricultura.agricultura_backend.model.Cultivo;
import com.tfg.agricultura.agricultura_backend.model.User;
import com.tfg.agricultura.agricultura_backend.repository.CultivoRepository;
import com.tfg.agricultura.agricultura_backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CultivoRepository cultivoRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, CultivoRepository cultivoRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cultivoRepository = cultivoRepository;
    }

    public void registerUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole("USER");

        userRepository.save(user); // Guarda primero al usuario

        // Asigna los cultivos iniciales al usuario
        assignInitialCultivos(user);
    }

    private void assignInitialCultivos(User user) {
        List<Cultivo> cultivosIniciales = cultivoRepository.findAll(); // Obtiene todos los cultivos existentes
        user.setCultivos(cultivosIniciales); // Relaciona los cultivos con el usuario
        userRepository.save(user); // Guarda los cambios
    }

    // Otros métodos...

    public boolean authenticateUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return true; // Credenciales válidas
            } else {
                throw new RuntimeException("Credenciales inválidas");
            }
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

}
