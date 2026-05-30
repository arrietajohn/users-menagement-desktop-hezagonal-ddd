package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetVehiculoByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetVehiculoByIdPort;
import com.jcaa.usersmanagement.domain.model.Vehiculomodel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetVehiculoByIdService implements GetVehiculoByIdUseCase {

    private final GetVehiculoByIdPort getVehiculoByIdPort;

    @Override
    public Vehiculomodel execute(Integer idBastidor) {
        return getVehiculoByIdPort.getById(idBastidor)
                .orElseThrow(() -> new RuntimeException(
                        "Vehiculo no encontrado en bastidor: " + idBastidor));
    }
}
