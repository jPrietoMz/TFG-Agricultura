package com.tfg.agricultura.agricultura_backend.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

//    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
//    private List<Cosecha> cosechas = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "usuario_cultivo",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "cultivo_id")
    )
    private List<Cultivo> cultivos = new ArrayList<>();

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Cultivo> getCultivos() {
        return cultivos;
    }

    public void setCultivos(List<Cultivo> cultivos) {
        this.cultivos = cultivos;
    }
}
