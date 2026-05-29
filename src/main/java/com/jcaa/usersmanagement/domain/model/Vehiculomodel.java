package com.jcaa.usersmanagement.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public final class Vehiculomodel {
    private final Integer idBastidor;
    private final java.math.BigDecimal precio;
    private final String cilindrada;
    private final String potencia;
    private final String estado;
    private final Integer idModelo;
    private final Integer idServicio;
}
