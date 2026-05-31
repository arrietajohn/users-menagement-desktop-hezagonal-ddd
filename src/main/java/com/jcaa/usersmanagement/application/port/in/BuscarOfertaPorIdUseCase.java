package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.Oferta;
import java.util.Optional;

public interface BuscarOfertaPorIdUseCase {
    Optional<Oferta> buscarPorId(Integer id);
}