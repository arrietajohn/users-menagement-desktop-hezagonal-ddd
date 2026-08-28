package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

import java.math.BigDecimal;

public record OfertaEmpleoEntity(
        String id,
        String titulo,
        String descripcion,
        String empresa,
        String ubicacion,
        BigDecimal salario,
        String estado
) {}