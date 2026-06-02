package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotNull;

public record DeleteDocumentoCommand(
    @NotNull(message = "El ID del documento es obligatorio") Long id) {}
