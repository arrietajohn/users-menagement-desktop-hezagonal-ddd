package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Vehiculomodel;
import java.util.Optional;

public interface GetVehiculoByIdPort {
    Optional<Vehiculomodel> getById(Integer idBastidor);
}
