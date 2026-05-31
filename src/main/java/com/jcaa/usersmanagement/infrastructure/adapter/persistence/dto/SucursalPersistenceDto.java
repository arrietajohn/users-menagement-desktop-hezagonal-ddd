package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record SucursalPersistenceDto(
        String id,
        String numero,
        String direccion,
        String codigoPostal,
        String ciudad,
        String bancoId
) {
}
