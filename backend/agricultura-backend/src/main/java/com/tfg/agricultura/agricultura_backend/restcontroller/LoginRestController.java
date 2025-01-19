package com.tfg.agricultura.agricultura_backend.restcontroller;

import com.tfg.agricultura.agricultura_backend.dto.LoginDTO;
import com.tfg.agricultura.agricultura_backend.security.JwtTokenProvider;
import com.tfg.agricultura.agricultura_backend.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LoginRestController {
    private final LoginService loginService;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginRestController(LoginService loginService, JwtTokenProvider jwtTokenProvider) {
        this.loginService = loginService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        boolean isAuthenticated = loginService.authenticate(loginDTO.getUsername(), loginDTO.getPassword());
        if (isAuthenticated) {
            // Generar el token JWT
            String token = jwtTokenProvider.generateToken(loginDTO.getUsername());

            // Devolver el token en la respuesta
            return ResponseEntity.ok().body(Map.of("token", token));
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}

