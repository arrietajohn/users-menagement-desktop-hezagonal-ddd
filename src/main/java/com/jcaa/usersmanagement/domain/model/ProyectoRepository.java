package com.jcaa.usersmanagement.domain.model;

import java.util.List;
import java.util.Optional;

public interface ProyectoRepository {
    Proyecto save(Proyecto proyecto);
    Optional<Proyecto> findById(Long id);
    List<Proyecto> findAll();
    void deleteById(Long id);
}
