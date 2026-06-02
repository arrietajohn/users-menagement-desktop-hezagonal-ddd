package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateEmpleadoCommand(
    @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
        String nombre,
    @NotBlank(message = "El apellido no puede estar vacío")
        @Size(max = 100, message = "El apellido no puede superar 100 caracteres")
        String apellido,
    @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El email no tiene formato válido")
        @Size(max = 150, message = "El email no puede superar 150 caracteres")
        String email,
    @NotBlank(message = "El cargo no puede estar vacío")
        @Size(max = 100, message = "El cargo no puede superar 100 caracteres")
        String cargo,
    @NotNull(message = "La fecha de contratación es obligatoria") String fechaContratacion,
    @NotBlank(message = "El estado no puede estar vacío") String estado) {}
