package com.jcaa.usersmanagement.domain.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProyectoRepository {
    Proyecto save(Proyecto proyecto);
    Optional<Proyecto> findById(Long id);
    List<Proyecto> findAll();
    void deleteById(Long id);

    //nuevos  casos de uso para la unidad 3
    void updateEstado(Long idProyecto, String nuevoEstado);
    void updateFechaFin(Long idProyecto, LocalDate nuevaFechaFin);
    void updatePromotor(Long idProyecto, Long nuevoIdPromotor);
}
