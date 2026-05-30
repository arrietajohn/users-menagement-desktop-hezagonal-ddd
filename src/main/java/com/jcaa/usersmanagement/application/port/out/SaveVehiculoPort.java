package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Vehiculomodel;

public interface SaveVehiculoPort {
    Vehiculomodel save(Vehiculomodel vehiculo);
}
