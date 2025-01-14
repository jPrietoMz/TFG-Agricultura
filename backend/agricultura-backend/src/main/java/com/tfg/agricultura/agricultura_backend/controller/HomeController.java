package com.tfg.agricultura.agricultura_backend.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "¡Bienvenido a la aplicación Agricultura!";
    }

}
