package com.jcaa.usersmanagement.application.trabajogrado;

import com.jcaa.usersmanagement.domain.model.trabajogrado.TrabajoGrado;
import java.util.List;
import java.util.Optional;

public interface TrabajoGradoRepository {
    void save(TrabajoGrado trabajoGrado);
    Optional<TrabajoGrado> findByNumeroOrden(Integer numeroOrden);
    List<TrabajoGrado> findAll();
    void update(TrabajoGrado trabajoGrado);
    void delete(Integer numeroOrden);
    boolean existsByNumeroOrden(Integer numeroOrden);
}
