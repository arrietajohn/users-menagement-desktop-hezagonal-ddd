package com.jcaa.usersmanagement.application.service.dto.query;

import jakarta.validation.constraints.NotBlank;

public record GetEspecieByIdQuery(
    @NotBlank(message = "El id es obligatorio") String id
) {}