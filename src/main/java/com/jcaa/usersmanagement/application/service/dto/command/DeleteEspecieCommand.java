package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;

public record DeleteEspecieCommand(
    @NotBlank(message = "El id es obligatorio") String id
) {}