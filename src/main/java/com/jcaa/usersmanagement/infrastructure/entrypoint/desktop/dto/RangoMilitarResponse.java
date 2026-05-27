package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record RangoMilitarResponse(
        String id,
        String codigo,
        String nombre,
        String descripcion,
        String lineaMilitar,
        int tiempoMinimoAscensoMeses
) {}
