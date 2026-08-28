package com.jcaa.usersmanagement.application.port.out.ofertaempleo;

import com.jcaa.usersmanagement.domain.model.OfertaEmpleoModel;

import java.util.List;
import java.util.Optional;

public interface OfertaEmpleoRepositoryPort {

    void save(OfertaEmpleoModel oferta);

    Optional<OfertaEmpleoModel> findById(String id);

    List<OfertaEmpleoModel> findAll();

    void update(OfertaEmpleoModel oferta);

    void delete(String id);
}