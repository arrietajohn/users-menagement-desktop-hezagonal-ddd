package com.jcaa.usersmanagement.application.service.dto.command;
import jakarta.validation.constraints.NotBlank;

public record DeleteSucursalCommand(
        @NotBlank(message = "El ID de la sucursal no puede estar vacío")
        String id
) {

}
