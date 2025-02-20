package com.tfg.agricultura.agricultura_backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Cosecha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaInicio; // Fecha de inicio de la cosecha
    private LocalDate fechaFin;    // Fecha de finalización de la cosecha
    private Double kilosObtenidos; // Cantidad de kilos obtenidos
    private Double precioObtenido; // Precio total obtenido por la cosecha

    @ManyToOne
    @JoinColumn(name = "cultivo_id") // Relación con el cultivo
    private Cultivo cultivo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;


    public Cosecha() {}

    public Cosecha(LocalDate fechaInicio, LocalDate fechaFin, Double kilosObtenidos, Double precioObtenido, Cultivo cultivo) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.kilosObtenidos = kilosObtenidos;
        this.precioObtenido = precioObtenido;
        this.cultivo = cultivo;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Double getKilosObtenidos() {
        return kilosObtenidos;
    }

    public void setKilosObtenidos(Double kilosObtenidos) {
        this.kilosObtenidos = kilosObtenidos;
    }

    public Double getPrecioObtenido() {
        return precioObtenido;
    }

    public void setPrecioObtenido(Double precioObtenido) {
        this.precioObtenido = precioObtenido;
    }

    public Cultivo getCultivo() {
        return cultivo;
    }

    public void setCultivo(Cultivo cultivo) {
        this.cultivo = cultivo;
    }

    public User getUsuario() {
        return usuario;
    }
    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
}
