package com.jcaa.usersmanagement.application.service.dto.query;
import jakarta.validation.constraints.NotBlank;

public record GetSucursalByIdQuery(@NotBlank(message = "el ID de la sucursal no puede estar vacío") String id) {

}
