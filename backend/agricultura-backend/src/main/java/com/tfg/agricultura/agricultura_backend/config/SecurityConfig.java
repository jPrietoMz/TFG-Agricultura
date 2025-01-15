package com.tfg.agricultura.agricultura_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register").permitAll() // Permitir acceso público a este endpoint
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // Solo accesible para ADMIN
                        .anyRequest().authenticated() // Requiere autenticación para otros endpoints
                )
                .csrf(csrf -> csrf.disable()); // Desactiva CSRF para pruebas (opcional)

        return http.build();
    }
}
