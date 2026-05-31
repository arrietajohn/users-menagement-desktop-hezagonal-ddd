package com.jcaa.usersmanagement.application.service.dto.command;

public record UpdateRepresentanteCommand(
        Integer id,
        String nombre,
        String telefono,
        String direccion
) {
}