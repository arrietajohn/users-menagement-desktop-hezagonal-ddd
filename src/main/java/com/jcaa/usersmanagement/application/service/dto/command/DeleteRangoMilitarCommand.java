package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;

public record DeleteRangoMilitarCommand(
        @NotBlank(message = "Id cannot be blank") String id
) {}
