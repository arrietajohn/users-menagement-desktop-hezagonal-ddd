package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Especie;
import com.jcaa.usersmanagement.domain.model.EspecieId;
import java.util.Optional;

public interface GetEspecieByIdPort {
    Optional<Especie> getById(EspecieId id);
}