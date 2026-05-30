package com.jcaa.usersmanagement.application.service.dto.command;

import com.jcaa.usersmanagement.domain.model.NombreCientifico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateEspecieCommand(
    @NotNull(message = "El nombre científico es obligatorio")
    NombreCientifico nombreCientifico,
    @NotBlank(message = "El nombre en español es obligatorio")
    String nombreEspañol,
    String descripcion
) {}