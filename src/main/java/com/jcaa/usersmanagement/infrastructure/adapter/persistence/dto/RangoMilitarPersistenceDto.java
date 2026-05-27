package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record RangoMilitarPersistenceDto(
        String id,
        String codigo,
        String nombre,
        String descripcion,
        String lineaMilitar,
        int tiempoMinimoAscensoMeses
) {}
