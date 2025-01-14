package com.tfg.agricultura.agricultura_backend.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public boolean authenticate(String username, String password) {
        // Lógica de autenticación (mock o conexión con base de datos)
        return "admin".equals(username) && "admin".equals(password);
    }
}
