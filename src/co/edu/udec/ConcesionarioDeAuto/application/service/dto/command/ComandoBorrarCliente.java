package edu.udec.ConcesionarioDeAuto.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;

public record ComandoBorrarCliente(
        @NotBlank(message = "la Id del cliente must not be blank") String clienteId
) {

}
