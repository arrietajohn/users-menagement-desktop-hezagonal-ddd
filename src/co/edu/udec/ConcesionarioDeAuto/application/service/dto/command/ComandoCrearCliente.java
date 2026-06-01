package edu.udec.ConcesionarioDeAuto.application.service.dto.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ComandoCrearCliente(
        @NotBlank(message = "id must not be blank")
        @Size(max = 10, message = "Su Numero de identidad no debe superar los 10 digitos") String clienteId,

        @NotBlank(message = "primer nombre must not be blank")
        @Size(max = 20, message = "name must have at least 3 characters") String primerNombre,
        @NotBlank(message = "segundo nombre not be blank")
        @Size(max = 20, message = "name must have at least 3 characters") String segundoNombre,
        @NotBlank(message = "primer apellido must not be blank")
        @Size(max = 20, message = "name must have at least 3 characters") String primerApellido,
        @NotBlank(message = "segundo apellido must not be blank")
        @Size(max = 20, message = "name must have at least 3 characters") String segundoApellido,
        @NotBlank(message = "direccion must not be blank")
        @Size(max = 50, message = "name must have at least 3 characters") String direccion,
        @NotBlank(message = "password must not be blank")
        @Size(min = 10, message = "password must have at least 8 characters") String telefono

)
{

}
