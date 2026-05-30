package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

import java.math.BigDecimal;

public record VehiculoPersistenceDto (
        Integer idBastidor,
        BigDecimal precio,
        String cilindrada,
        String potencia,
        String estado,
        Integer idModelo,
        Integer idServicio
) {}
