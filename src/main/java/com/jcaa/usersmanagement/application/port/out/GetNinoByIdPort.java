package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.nino.Nino;

import java.util.Optional;

public interface GetNinoByIdPort {
    Optional<Nino> getById(Long id);
}
