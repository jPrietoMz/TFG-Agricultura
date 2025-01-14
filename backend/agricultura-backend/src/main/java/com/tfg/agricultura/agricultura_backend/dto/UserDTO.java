package com.tfg.agricultura.agricultura_backend.dto;

public class UserDTO {

    private String username;
    private String password;
    private String role;

    // Constructor vacío
    public UserDTO() {}

    // Constructor con parámetros
    public UserDTO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
