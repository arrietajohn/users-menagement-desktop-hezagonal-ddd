package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateProyectoCommand(
    @NotNull(message = "El id del proyecto es obligatorio") Long id,
    @NotBlank(message = "El nombre clave no puede estar vacío")
        @Size(max = 50, message = "El nombre clave no puede superar 50 caracteres")
        String nombreClave,
    @NotBlank(message = "La denominación no puede estar vacía")
        @Size(max = 150, message = "La denominación no puede superar 150 caracteres")
        String denominacion,
    @NotNull(message = "La fecha de inicio es obligatoria") String fechaInicio,
    String fechaFin,
    @NotBlank(message = "El estado no puede estar vacío") String estado,
    Long promotorId) {}
