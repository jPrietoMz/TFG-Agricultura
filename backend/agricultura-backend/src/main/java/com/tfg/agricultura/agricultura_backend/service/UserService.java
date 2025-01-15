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
        // Verificar si el usuario ya existe
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        // Crear y guardar el usuario
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole("USER");
        userRepository.save(user);

        // Asignar cultivos iniciales
        assignInitialCultivos(user);
    }

    private void assignInitialCultivos(User user) {
        List<Cultivo> cultivosIniciales = List.of(
                new Cultivo("Higos", "Cultivo de higos", user),
                new Cultivo("Aceituna Verde", "Cultivo de aceituna verde", user),
                new Cultivo("Aceituna Negra", "Cultivo de aceituna negra", user),
                new Cultivo("Uva", "Cultivo de uva", user)
        );
        cultivoRepository.saveAll(cultivosIniciales);
    }

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
    // Otros métodos de servicio...
}
