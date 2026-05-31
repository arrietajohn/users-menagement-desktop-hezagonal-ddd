package com.jcaa.usersmanagement.application.service.dto.command;

public record CreateRepresentanteCommand(
        Integer id,
        String nombre,
        String telefono,
        String direccion
) {
}