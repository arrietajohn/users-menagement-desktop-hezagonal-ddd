package com.jcaa.usersmanagement.domain.model.nino;

import com.jcaa.usersmanagement.domain.model.nino.vo.EstadoInscripcion;
import com.jcaa.usersmanagement.domain.model.nino.vo.Matricula;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Nino {

    private final Long id;
    private final Matricula matricula;
    private String nombreCompleto;
    private LocalDate fechaNacimiento;
    private LocalDate fechaIngreso;
    private LocalDate fechaBaja;
    private EstadoInscripcion estadoInscripcion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor para crear un nuevo niño
    public Nino(Long id, Matricula matricula, String nombreCompleto,
                LocalDate fechaNacimiento, LocalDate fechaIngreso) {

        this.id = id;
        this.matricula = matricula;
        this.nombreCompleto = validarNombre(nombreCompleto);
        this.fechaNacimiento = validarFechaNacimiento(fechaNacimiento);
        this.fechaIngreso = validarFechaIngreso(fechaIngreso);
        this.estadoInscripcion = EstadoInscripcion.ACTIVO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Reglas de negocio
    public void darDeBaja() {
        if (this.estadoInscripcion == EstadoInscripcion.BAJA) {
            throw new IllegalStateException("El niño ya se encuentra dado de baja");
        }
        this.fechaBaja = LocalDate.now();
        this.estadoInscripcion = EstadoInscripcion.BAJA;
        this.updatedAt = LocalDateTime.now();
    }

    // Métodos de validación privados
    private String validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo es obligatorio");
        }
        return nombre.trim();
    }

    private LocalDate validarFechaNacimiento(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria");
        }
        return fecha;
    }

    private LocalDate validarFechaIngreso(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de ingreso es obligatoria");
        }
        return fecha;
    }

    // Getters
    public Long getId() { return id; }
    public Matricula getMatricula() { return matricula; }
    public String getNombreCompleto() { return nombreCompleto; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public LocalDate getFechaBaja() { return fechaBaja; }
    public EstadoInscripcion getEstadoInscripcion() { return estadoInscripcion; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}