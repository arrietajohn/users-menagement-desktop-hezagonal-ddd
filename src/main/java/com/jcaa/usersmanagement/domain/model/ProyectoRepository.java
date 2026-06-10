package com.jcaa.usersmanagement.domain.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProyectoRepository {
    Proyecto save(Proyecto proyecto);
    Optional<Proyecto> findById(Long id);
    List<Proyecto> findAll();
    void deleteById(Long id);

    // nuevos casos de uso para la unidad 3
    void updateEstado(Long idProyecto, String nuevoEstado);
    void updateFechaFin(Long idProyecto, LocalDate nuevaFechaFin);
    void updatePromotor(Long idProyecto, Long nuevoIdPromotor);

    // nevos casos de uso - UNIDAD 4

    // Consulta CEA N° 1: Lista de proyectos en curso (Estado = 'ACTIVO' o 'EN_CURSO')
    List<Proyecto> findProyectosEnCurso();

    // Consulta CEA N° 7: Proyectos completados dentro de un rango de fechas específico
    List<Proyecto> findProyectosCompletadosPorRangoFechas(LocalDate inicio, LocalDate fin);

    // Consulta CEA Adaptada N° 3: Proyectos asignados a un promotor específico por su ID
    List<Proyecto> findByPromotorId(Long idPromotor);

    // Consulta CEA Adaptada N° 4: Filtrar proyectos por cualquier estado operativo específico
    List<Proyecto> findByEstadoEspecifico(String estado);

    // Consulta CEA Adaptada N° 5: Búsqueda dinámica por coincidencia parcial de texto en la denominación
    List<Proyecto> findByDenominacionLike(String termino);
}
