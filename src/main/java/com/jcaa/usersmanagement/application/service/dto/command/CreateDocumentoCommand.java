package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateDocumentoCommand(
    @NotBlank(message = "El título no puede estar vacío")
        @Size(max = 200, message = "El título no puede superar 200 caracteres")
        String titulo,
    @NotBlank(message = "El tipo no puede estar vacío") String tipo,
    @Size(max = 5000, message = "El contenido no puede superar 5000 caracteres")
        String contenido,
    @NotNull(message = "La fecha de creación es obligatoria") String fechaCreacion,
    @NotBlank(message = "El estado no puede estar vacío") String estado,
    @NotNull(message = "El ID del autor es obligatorio") Long autorId) {}
