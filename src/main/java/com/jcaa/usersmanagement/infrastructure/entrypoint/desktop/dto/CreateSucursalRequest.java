package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record CreateSucursalRequest(
        String id,
        String numero,
        String direccion,
        String codigoPostal,
        String ciudad,
        String bancoId) {}