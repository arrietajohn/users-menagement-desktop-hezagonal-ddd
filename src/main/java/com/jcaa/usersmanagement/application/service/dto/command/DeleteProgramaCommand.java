package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotNull;

public record DeleteProgramaCommand(
    @NotNull(message = "id must not be null") Long id) {
}
