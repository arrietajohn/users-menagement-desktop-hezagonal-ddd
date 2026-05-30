package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.domain.ports.ProyectoRepository;

public class CambiarPromotorProyectoService {
    private final ProyectoRepository repository;

    public CambiarPromotorProyectoService(ProyectoRepository repository) {
        this.repository = repository;
    }

    public void execute(Long idProyecto, Long nuevoIdPromotor) {
        if (idProyecto == null || nuevoIdPromotor == null) {
            throw new IllegalArgumentException("El ID del proyecto y el ID del nuevo promotor son obligatorios.");
        }
        this.repository.updatePromotor(idProyecto, nuevoIdPromotor);
    }
}
