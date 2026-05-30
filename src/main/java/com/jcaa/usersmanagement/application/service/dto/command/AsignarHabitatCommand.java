package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotNull;

public record AsignarHabitatCommand(

    @NotNull(message = "El id de especie es obligatorio")
    Integer idEspecie,

    @NotNull(message = "El id de habitat es obligatorio")
    Integer idHabitat
) {}
