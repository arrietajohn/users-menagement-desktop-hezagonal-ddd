package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.ofertaempleo.dto;

import java.math.BigDecimal;

public record CreateOfertaEmpleoRequest(
        String id,
        String titulo,
        String descripcion,
        String empresa,
        String ubicacion,
        BigDecimal salario,
        String estado
) {}