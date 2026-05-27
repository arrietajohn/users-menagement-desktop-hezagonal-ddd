package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NinoPersistenceDto(
        Long id,
        String numero_matricula,
        String nombre_completo,
        LocalDate fecha_nacimiento,
        LocalDate fecha_ingreso,
        LocalDate fecha_baja,
        BigDecimal costo_mensual,
        String estado,
        String createdAt,
        String updatedAt
) {}