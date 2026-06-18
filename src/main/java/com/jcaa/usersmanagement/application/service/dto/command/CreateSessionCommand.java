package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateSessionCommand(
        @NotBlank(message = "id must not be blank") String id,
        @NotBlank(message = "SalaId must not be blank") String salaId,
        @NotBlank(message = "investigacionId must not be blank") String investigacionId,
        @NotBlank(message = "ponenteId must not be blank") String ponenteId,
        @NotBlank(message = "chairmanId must not be blank") String chairmanId,
        @NotNull(message = "fecha must not be blank")LocalDate fecha,
        @NotNull(message = "horaInicio must not be blank") LocalTime horaInicio,
        @NotNull(message = "horaFin must not be blank")LocalTime horaFin
)
{

}

