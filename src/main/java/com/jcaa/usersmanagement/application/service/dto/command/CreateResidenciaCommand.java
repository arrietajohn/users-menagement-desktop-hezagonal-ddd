package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record CreateResidenciaCommand(
    @NotNull(message = "personaId must not be null")
        @Positive(message = "personaId must be a positive integer")
        Integer personaId,
    @NotBlank(message = "municipioId must not be blank")
        @Size(max = 10, message = "municipioId must have at most 10 characters")
        String municipioId,
    @Size(max = 100, message = "paisExtranjero must have at most 100 characters")
        String paisExtranjero,
    @Size(max = 150, message = "direccion must have at most 150 characters")
        String direccion,
    @NotNull(message = "fechaInicio must not be null") LocalDate fechaInicio) {
}
