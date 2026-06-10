package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.domain.model.Proyecto;
import com.jcaa.usersmanagement.domain.model.ProyectoRepository;
import java.time.LocalDate;
import java.util.List;

public class BuscarProyectosPorRangoFechasService {
    private final ProyectoRepository repository;

    public BuscarProyectosPorRangoFechasService(ProyectoRepository repository) {
        this.repository = repository;
    }

    public List<Proyecto> execute(LocalDate inicio, LocalDate fin) {
        if (inicio == null || fin == null || inicio.isAfter(fin)) {
            throw new IllegalArgumentException("El rango de fechas es inválido. La fecha inicial no puede ser posterior a la fecha fin.");
        }
        return repository.findProyectosCompletadosPorRangoFechas(inicio, fin);
    }
}