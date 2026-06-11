package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.domain.model.Proyecto;
import com.jcaa.usersmanagement.domain.model.ProyectoRepository;
import java.util.List;

public class ListarProyectosEnCursoService {
    private final ProyectoRepository repository;

    public ListarProyectosEnCursoService(ProyectoRepository repository) {
        this.repository = repository;
    }

    public List<Proyecto> execute() {
        return repository.findProyectosEnCurso();
    }
}