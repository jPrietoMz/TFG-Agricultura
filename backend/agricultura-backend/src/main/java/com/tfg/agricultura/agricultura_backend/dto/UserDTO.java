package com.tfg.agricultura.agricultura_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;



public class UserDTO {

    @NotBlank(message = "El nombre de usuario no puede estar vacío.")
    private String username;
    @NotBlank(message = "La contraseña no puede estar vacía.")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres.")
    private String password;
//    private String role;

    // Constructor vacío
    public UserDTO() {}

    // Constructor con parámetros
    public UserDTO(String username, String password, String role) {
        this.username = username;
        this.password = password;
//        this.role = role;
    }

    // Getters y setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //DEFINO ROLE POR DEFECTO COMO USER PORQUE LA GENTE QUE SE REGISTRA NO NECESITA TANTOS PEMR
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
}
