package edu.udec.ConcesionarioDeAuto.application.service.dto.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ComandoIngresarCliente(

        @NotBlank(message = "password must not be blank")
        @Size(max = 10 , message = "password must have at least 8 characters")
        String clienteId,
        @NotBlank(message = "password must not be blank")
        @Size(max = 20, message = "password must have at least 8 characters")
        String primerNombre)
{

}