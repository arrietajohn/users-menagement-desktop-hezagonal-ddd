package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

public record SucursalEntity(
        String id,
        String numero,
        String direccion,
        String codigoPostal,
        String ciudad,
        String bancoId
) {
}
