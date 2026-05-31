package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Oferta;
import java.util.List;
import java.util.Optional;

public interface OfertaRepositoryPort {
    void guardar(Oferta oferta);
    List<Oferta> buscarPorSubasta(Integer idSubasta);
    Optional<Oferta> buscarPorId(Integer id);
}