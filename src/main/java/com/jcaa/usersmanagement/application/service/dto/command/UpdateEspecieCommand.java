package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;

public record UpdateEspecieCommand(
    @NotBlank(message = "El id es obligatorio") String id,
    @NotBlank(message = "El nombre en español es obligatorio") String nombreEspañol,
    String descripcion
) {}