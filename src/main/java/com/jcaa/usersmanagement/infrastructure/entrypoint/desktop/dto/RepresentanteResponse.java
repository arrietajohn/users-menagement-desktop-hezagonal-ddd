package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record RepresentanteResponse(
        Integer id,
        String nombre,
        String telefono,
        String direccion
) {
}