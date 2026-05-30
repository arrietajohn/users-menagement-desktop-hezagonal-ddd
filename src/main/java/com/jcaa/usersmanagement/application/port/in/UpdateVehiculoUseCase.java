package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.Vehiculomodel;

public interface UpdateVehiculoUseCase {
    Vehiculomodel execute(Integer idBastidor, java.math.BigDecimal precio,
                          String cilindrada, String potencia, String estado,
                          Integer idModelo, Integer idServicio);
}
