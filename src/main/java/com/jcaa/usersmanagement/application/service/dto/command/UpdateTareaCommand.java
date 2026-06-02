package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTareaCommand(
    @NotNull(message = "El ID de la tarea es obligatorio") Long id,
    @NotBlank(message = "El título no puede estar vacío")
        @Size(max = 200, message = "El título no puede superar 200 caracteres")
        String titulo,
    @Size(max = 1000, message = "La descripción no puede superar 1000 caracteres")
        String descripcion,
    @NotBlank(message = "La prioridad no puede estar vacía") String prioridad,
    @NotBlank(message = "El estado no puede estar vacío") String estado,
    String fechaVencimiento,
    @NotNull(message = "El ID del empleado asignado es obligatorio") Long empleadoId) {}
