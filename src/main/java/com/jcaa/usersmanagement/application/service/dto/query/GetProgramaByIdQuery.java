package com.jcaa.usersmanagement.application.service.dto.query;

import jakarta.validation.constraints.NotNull;

public record GetProgramaByIdQuery(
    @NotNull(message = "id must not be null") Long id) {
}
