package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;

public record CreateAerolineaCommand(

        Integer idAerolinea,

        @NotBlank(message = "nombre no puede estar vacío")
        String nombre,

        String paisOrigen
) {
}