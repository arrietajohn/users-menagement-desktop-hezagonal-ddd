package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveVehiculoPort;
import com.jcaa.usersmanagement.domain.model.Vehiculomodel;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;

@RequiredArgsConstructor
public final class CreateVehiculoService implements CreateVehiculoUseCase {
    private final SaveVehiculoPort saveVehiculoPort;

    @Override
    public Vehiculomodel execute(Integer idBastidor, BigDecimal precio,
                                 String cilindrada, String potencia, String estado,
                                 Integer idModelo, Integer idServicio) {
        final Vehiculomodel vehiculo = new Vehiculomodel(
                idBastidor, precio, cilindrada, potencia, estado, idModelo, idServicio);
        return saveVehiculoPort.save(vehiculo);
    }
}
