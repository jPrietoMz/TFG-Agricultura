//package com.tfg.agricultura.agricultura_backend.controller;
//
//import com.tfg.agricultura.agricultura_backend.security.JwtTokenProvider; // Asegúrate de que tienes esta clase
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtTokenProvider jwtTokenProvider;
//
//    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
//        // Extraer username y password del cuerpo de la solicitud
//        String username = loginRequest.get("username");
//        String password = loginRequest.get("password");
//
//        // Autenticar al usuario
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password)
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // Generar el token JWT
//        String token = jwtTokenProvider.generateToken(authentication);
//
//        // Devolver el token al cliente
//        return ResponseEntity.ok(Map.of("token", token));
//    }
//}
