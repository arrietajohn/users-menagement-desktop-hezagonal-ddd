package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.out.UpdateVehiculoPort;
import com.jcaa.usersmanagement.domain.model.Vehiculomodel;
import com.mysql.cj.util.StringInspector;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;

@RequiredArgsConstructor
public final class UpdateVehiculoService implements UpdateVehiculoUseCase {

    private final UpdateVehiculoPort updateVehiculoPort;

    @Override
    public Vehiculomodel execute(Integer idBastidor, BigDecimal precio,
                                 String cilindrada, String potencia, String estado,
                                 Integer idModelo, Integer idServicio) {
        final Vehiculomodel vehiculo = new Vehiculomodel(
                idBastidor, precio, cilindrada, potencia, estado, idModelo, idServicio);
        return updateVehiculoPort.update(vehiculo);
    }
}
