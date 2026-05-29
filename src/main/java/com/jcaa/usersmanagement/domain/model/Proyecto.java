package com.jcaa.usersmanagement.domain.model;

import java.time.LocalDate;

public class Proyecto {
    private Long idProyecto;
    private String nombreClave;
    private String denominacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private Long idPromotor;

    public Proyecto() {}

    public Proyecto(Long idProyecto, String nombreClave, String denominacion, LocalDate fechaInicio, LocalDate fechaFin, String estado, Long idPromotor) {
        this.idProyecto = idProyecto;
        this.nombreClave = nombreClave;
        this.denominacion = denominacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.idPromotor = idPromotor;
    }

    // Getters y Setters
    public Long getIdProyecto() { return idProyecto; }
    public void setIdProyecto(Long idProyecto) { this.idProyecto = idProyecto; }
    public String getNombreClave() { return nombreClave; }
    public void setNombreClave(String nombreClave) { this.nombreClave = nombreClave; }
    public String getDenominacion() { return denominacion; }
    public void setDenominacion(String denominacion) { this.denominacion = denominacion; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Long getIdPromotor() { return idPromotor; }
    public void setIdPromotor(Long idPromotor) { this.idPromotor = idPromotor; }
}
