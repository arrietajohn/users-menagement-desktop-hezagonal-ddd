package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.domain.model.Proyecto;
import com.jcaa.usersmanagement.domain.model.ProyectoRepository;
import java.util.List;

public class BuscarProyectosPorDenominacionService {
    private final ProyectoRepository repository;

    public BuscarProyectosPorDenominacionService(ProyectoRepository repository) {
        this.repository = repository;
    }

    public List<Proyecto> execute(String termino) {
        if (termino == null || termino.trim().length() < 2) {
            throw new IllegalArgumentException("Debe ingresar al menos dos caracteres para realizar la búsqueda.");
        }
        return repository.findByDenominacionLike(termino.trim());
    }
}