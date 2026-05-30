package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RegistrarExamenMedicoCommand(

    @NotNull(message = "El id de especie es obligatorio")
    String idEspecie,

    @NotNull(message = "El id de cuidador es obligatorio")
    Integer idCuidador,

    @NotBlank(message = "El diagnóstico es obligatorio")
    String diagnostico,

    String tratamiento,
    String observaciones
) {}