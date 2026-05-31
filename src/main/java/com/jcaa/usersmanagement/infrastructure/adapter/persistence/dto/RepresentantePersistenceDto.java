package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record RepresentantePersistenceDto(
        Integer idRepresentante,
        String nombre,
        String telefono,
        String direccion
) {
}
