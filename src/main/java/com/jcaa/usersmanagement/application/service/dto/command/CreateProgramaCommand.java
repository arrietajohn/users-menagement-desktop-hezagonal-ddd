package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateProgramaCommand(
    @NotNull(message = "id must not be null") Long id,
    @NotBlank(message = "nombre must not be blank")
        @Size(min = 3, message = "nombre must have at least 3 characters")
        String nombre,
    @NotBlank(message = "genero must not be blank") String genero) {
}
