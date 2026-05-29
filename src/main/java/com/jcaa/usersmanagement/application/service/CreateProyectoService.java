package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateProyectoUseCase;
import com.jcaa.usersmanagement.domain.model.Proyecto;
import com.jcaa.usersmanagement.domain.model.ProyectoRepository;

public class CreateProyectoService implements CreateProyectoUseCase {

    private final ProyectoRepository proyectoRepository;

    public CreateProyectoService(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public Proyecto execute(Proyecto proyecto) {
        if (proyecto.getNombreClave() == null || proyecto.getNombreClave().isEmpty()) {
            throw new RuntimeException("El nombre en clave es obligatorio para registrar el proyecto.");
        }
        return proyectoRepository.save(proyecto);
    }
}