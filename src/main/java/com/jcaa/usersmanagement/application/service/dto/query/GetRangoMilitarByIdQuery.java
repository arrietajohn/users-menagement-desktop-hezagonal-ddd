package com.jcaa.usersmanagement.application.service.dto.query;

import jakarta.validation.constraints.NotBlank;

public record GetRangoMilitarByIdQuery(
        @NotBlank(message = "Id cannot be blank") String id
) {}
