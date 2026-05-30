package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.domain.ports.ProyectoRepository;
import java.time.LocalDate;

public class ProrrogarFechaFinService {
    private final ProyectoRepository repository;

    public ProrrogarFechaFinService(ProyectoRepository repository) {
        this.repository = repository;
    }

    public void execute(Long idProyecto, LocalDate nuevaFechaFin) {
        if (idProyecto == null || nuevaFechaFin == null) {
            throw new IllegalArgumentException("El ID del proyecto y la nueva fecha son obligatorios.");
        }
        this.repository.updateFechaFin(idProyecto, nuevaFechaFin);
    }
}