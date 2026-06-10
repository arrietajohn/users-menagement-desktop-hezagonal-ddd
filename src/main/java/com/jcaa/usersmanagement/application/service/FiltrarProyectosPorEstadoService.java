package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.domain.model.Proyecto;
import com.jcaa.usersmanagement.domain.model.ProyectoRepository;
import java.util.List;

public class FiltrarProyectosPorEstadoService {
    private final ProyectoRepository repository;

    public FiltrarProyectosPorEstadoService(ProyectoRepository repository) {
        this.repository = repository;
    }

    public List<Proyecto> execute(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado operativo a filtrar no puede estar vacío.");
        }
        return repository.findByEstadoEspecifico(estado.trim().toUpperCase());
    }
}