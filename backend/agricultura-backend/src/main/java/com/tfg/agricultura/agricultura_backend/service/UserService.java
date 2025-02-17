package com.tfg.agricultura.agricultura_backend.service;

import com.tfg.agricultura.agricultura_backend.dto.UserDTO;
import com.tfg.agricultura.agricultura_backend.model.Cultivo;
import com.tfg.agricultura.agricultura_backend.model.User;
import com.tfg.agricultura.agricultura_backend.repository.CultivoRepository;
import com.tfg.agricultura.agricultura_backend.repository.UserRepository;
import com.tfg.agricultura.agricultura_backend.security.JwtTokenProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CultivoRepository cultivoRepository;
    private final JwtTokenProvider jwtTokenProvider; // 🔹 Inyectamos el generador de tokens

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       CultivoRepository cultivoRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cultivoRepository = cultivoRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 🔹 Registrar usuario con validación
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

    // 🔹 Asignar cultivos iniciales al usuario
    private void assignInitialCultivos(User user) {
        List<Cultivo> cultivosIniciales = cultivoRepository.findAll();
        user.setCultivos(cultivosIniciales);
        userRepository.save(user);
    }

    // 🔹 Método para autenticar y generar token
    public String authenticateAndGenerateToken(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            boolean match = passwordEncoder.matches(password, user.getPassword());

            if (match) {
                // 🔹 Generar token JWT
                String token = jwtTokenProvider.generateToken(username);

                // 🔹 Configurar Spring Security con el usuario autenticado
                UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                        user.getUsername(), user.getPassword(), List.of());
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                return token; // 🔹 Devolvemos el token al frontend
            }
        }
        throw new RuntimeException("Credenciales inválidas");
    }
}
