package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateRangoMilitarCommand(
        @NotBlank(message = "Id cannot be blank") String id,
        @NotBlank(message = "Codigo cannot be blank")
        @Pattern(regexp = "[A-Z0-9]{2,10}", message = "Codigo must be 2-10 uppercase alphanumeric characters")
        String codigo,
        @NotBlank(message = "Nombre cannot be blank") String nombre,
        String descripcion,
        @NotBlank(message = "Linea militar cannot be blank") String lineaMilitar,
        @Min(value = 0, message = "Tiempo minimo ascenso must be zero or positive") int tiempoMinimoAscensoMeses
) {}
