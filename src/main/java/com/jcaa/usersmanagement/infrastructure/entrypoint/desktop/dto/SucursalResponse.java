package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record SucursalResponse(
        String id,
        String numero,
        String direccion,
        String codigoPostal,
        String ciudad,
        String bancoId) {}