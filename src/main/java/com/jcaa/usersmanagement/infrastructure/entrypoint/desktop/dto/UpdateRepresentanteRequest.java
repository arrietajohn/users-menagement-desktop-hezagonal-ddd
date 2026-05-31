package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record UpdateRepresentanteRequest(
        Integer id,
        String nombre,
        String telefono,
        String direccion
) {
}