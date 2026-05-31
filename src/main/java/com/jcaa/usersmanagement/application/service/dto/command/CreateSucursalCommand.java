package com.jcaa.usersmanagement.application.service.dto.command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateSucursalCommand(
        @NotBlank(message = "El número de sucursal no puede estar vacío")
        String numero,
        @NotBlank(message = "La dirección no puede estar vacía")
        String direccion,
        @NotBlank(message = "El código postal no puede estar vacío")
        String codigoPostal,
        @NotBlank(message = "La ciudad no puede estar vacía")
        String ciudad,
        @NotNull(message = "El ID del banco no puede ser nulo")
        Integer bancoId)
{

}
