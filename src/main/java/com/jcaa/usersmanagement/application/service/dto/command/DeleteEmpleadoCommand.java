package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotNull;

public record DeleteEmpleadoCommand(
    @NotNull(message = "El ID del empleado es obligatorio") Long id) {}
