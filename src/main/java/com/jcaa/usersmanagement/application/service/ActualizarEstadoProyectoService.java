package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.domain.model.ProyectoRepository;

public class ActualizarEstadoProyectoService {
    private final ProyectoRepository repository;

    public ActualizarEstadoProyectoService(ProyectoRepository repository) {
        this.repository = repository;
    }

    public void execute(Long idProyecto, String nuevoEstado) {
        if (idProyecto == null || nuevoEstado == null || nuevoEstado.isBlank()) {
            throw new IllegalArgumentException("El ID del proyecto y el estado son obligatorios.");
        }
        this.repository.updateEstado(idProyecto, nuevoEstado);
    }
}