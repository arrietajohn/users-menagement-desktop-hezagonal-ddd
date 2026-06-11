package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllProyectosUseCase;
import com.jcaa.usersmanagement.domain.model.Proyecto;
import com.jcaa.usersmanagement.domain.model.ProyectoRepository;
import java.util.List;

public class GetAllProyectosService implements GetAllProyectosUseCase {

    private final ProyectoRepository proyectoRepository;

    public GetAllProyectosService(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public List<Proyecto> execute() {
        return proyectoRepository.findAll();
    }
}