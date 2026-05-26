package com.jcaa.usersmanagement.application.service.nino.dto;

import com.jcaa.usersmanagement.domain.model.nino.Nino;
import com.jcaa.usersmanagement.domain.model.nino.vo.Matricula;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NinoResponse {

    private final Long id;
    private final Matricula matricula;
    private final String nombreCompleto;
    private final LocalDate fechaNacimiento;
    private final LocalDate fechaIngreso;
    private final LocalDate fechaBaja;
    private final String estado;

    public NinoResponse(Nino nino) {
        this.id = nino.getId();
        this.matricula = nino.getMatricula();
        this.nombreCompleto = nino.getNombreCompleto();
        this.fechaNacimiento = nino.getFechaNacimiento();
        this.fechaIngreso = nino.getFechaIngreso();
        this.fechaBaja = nino.getFechaBaja();
        this.estado = nino.getEstadoInscripcion() != null ? nino.getEstadoInscripcion().name() : "ACTIVO";
    }

    // Getters
    public Long getId() { return id; }
    public Matricula getMatricula() { return matricula; }
    public String getNombreCompleto() { return nombreCompleto; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public LocalDate getFechaBaja() { return fechaBaja; }
    public String getEstado() { return estado; }
}