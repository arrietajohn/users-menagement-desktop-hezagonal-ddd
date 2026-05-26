package com.jcaa.usersmanagement.application.service.nino.command;

import com.jcaa.usersmanagement.domain.model.nino.vo.Matricula;
import java.time.LocalDate;

public class CreateNinoCommand {

    private final Matricula matricula;
    private final String nombreCompleto;
    private final LocalDate fechaNacimiento;
    private final LocalDate fechaIngreso;

    public CreateNinoCommand(Matricula matricula, String nombreCompleto,
                             LocalDate fechaNacimiento, LocalDate fechaIngreso) {
        this.matricula = matricula;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
    }

    public Matricula getMatricula() { return matricula; }
    public String getNombreCompleto() { return nombreCompleto; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
}
