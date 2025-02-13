package com.tfg.agricultura.agricultura_backend.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tratamiento")
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cultivo_id", nullable = false)
    private Cultivo cultivo;

    @Temporal(TemporalType.DATE)
    private Date fechaAplicacion;

    private String producto;
    private double dosis;
    private String unidadMedida;
    private String metodoAplicacion;
    private String observaciones;

    // 🔹 Constructor vacío
    public Tratamiento() {}

    // 🔹 Constructor con parámetros
    public Tratamiento(Cultivo cultivo, Date fechaAplicacion, String producto, double dosis, String unidadMedida, String metodoAplicacion, String observaciones) {
        this.cultivo = cultivo;
        this.fechaAplicacion = fechaAplicacion;
        this.producto = producto;
        this.dosis = dosis;
        this.unidadMedida = unidadMedida;
        this.metodoAplicacion = metodoAplicacion;
        this.observaciones = observaciones;
    }

    // 🔹 Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Cultivo getCultivo() { return cultivo; }
    public void setCultivo(Cultivo cultivo) { this.cultivo = cultivo; }

    public Date getFechaAplicacion() { return fechaAplicacion; }
    public void setFechaAplicacion(Date fechaAplicacion) { this.fechaAplicacion = fechaAplicacion; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public double getDosis() { return dosis; }
    public void setDosis(double dosis) { this.dosis = dosis; }

    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }

    public String getMetodoAplicacion() { return metodoAplicacion; }
    public void setMetodoAplicacion(String metodoAplicacion) { this.metodoAplicacion = metodoAplicacion; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
