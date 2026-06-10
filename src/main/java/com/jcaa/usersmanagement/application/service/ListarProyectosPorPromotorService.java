package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.domain.model.Proyecto;
import com.jcaa.usersmanagement.domain.model.ProyectoRepository;
import java.util.List;

public class ListarProyectosPorPromotorService {
    private final ProyectoRepository repository;

    public ListarProyectosPorPromotorService(ProyectoRepository repository) {
        this.repository = repository;
    }

    public List<Proyecto> execute(Long idPromotor) {
        if (idPromotor == null || idPromotor <= 0) {
            throw new IllegalArgumentException("El ID del promotor debe ser un número válido mayor a cero.");
        }
        return repository.findByPromotorId(idPromotor);
    }
}