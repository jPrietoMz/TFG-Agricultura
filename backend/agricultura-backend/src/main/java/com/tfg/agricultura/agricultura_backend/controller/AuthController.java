package com.tfg.agricultura.agricultura_backend.controller;

import com.tfg.agricultura.agricultura_backend.dto.UserDTO;
import com.tfg.agricultura.agricultura_backend.model.User;
import com.tfg.agricultura.agricultura_backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody User user) {
//        try {
//            userService.registerUser(user); // Ahora recibe un `User` en lugar de `UserDTO`
//            return ResponseEntity.ok("Usuario registrado exitosamente.");
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body("Error en el registro: " + e.getMessage());
//        }
//    }

//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
//        try {
//            userService.registerUser(userDTO);
//            return ResponseEntity.ok("Usuario registrado exitosamente.");
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body("Error en el registro: " + e.getMessage());
//        }
//    }
//    @PostMapping("/register")
//    public ResponseEntity<User> registerUser(@RequestBody User user) {
//        User newUser = userService.registerUser(user);
//        return ResponseEntity.ok(newUser);
//    }
}



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
