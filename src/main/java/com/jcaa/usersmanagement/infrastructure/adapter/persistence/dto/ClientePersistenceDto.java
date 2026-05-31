package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record ClientePersistenceDto(
        Integer idCliente,
        String nombre,
        String apellido,
        String email,
        String telefono,
        String direccion
) {}
