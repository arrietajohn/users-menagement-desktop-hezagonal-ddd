package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotNull;

public record DeleteTareaCommand(
    @NotNull(message = "El ID de la tarea es obligatorio") Long id) {}
