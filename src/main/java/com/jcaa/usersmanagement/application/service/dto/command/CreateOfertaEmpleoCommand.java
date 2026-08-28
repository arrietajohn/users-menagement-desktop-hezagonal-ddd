package com.jcaa.usersmanagement.application.service.dto.command;

import java.math.BigDecimal;

public record CreateOfertaEmpleoCommand(
        String id,
        String titulo,
        String descripcion,
        String empresa,
        String ubicacion,
        BigDecimal salario,
        String estado
) {}