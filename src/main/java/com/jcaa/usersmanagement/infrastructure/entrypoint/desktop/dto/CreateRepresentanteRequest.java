package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record CreateRepresentanteRequest(
        Integer id,
        String nombre,
        String telefono,
        String direccion
) {
}