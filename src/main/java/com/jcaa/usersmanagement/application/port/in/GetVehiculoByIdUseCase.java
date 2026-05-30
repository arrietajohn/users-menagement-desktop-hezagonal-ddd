package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.Vehiculomodel;

public interface GetVehiculoByIdUseCase {
    Vehiculomodel execute(Integer idBastidor);
}
