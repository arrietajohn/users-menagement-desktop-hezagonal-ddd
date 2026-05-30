package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

import java.math.BigDecimal;

public record ActivityResponse(
        String id,
        String nombre,
        String descripción,
        String Dia,
        String cronograma,
        BigDecimal precio,
        boolean EsGratis,
        String IdEmpleado,
        int hotelId) {}