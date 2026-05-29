package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DeleteResidenciaCommand(
    @NotNull(message = "id must not be null")
        @Positive(message = "id must be a positive integer")
        Integer id) {
}
