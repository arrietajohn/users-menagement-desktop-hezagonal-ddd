package edu.udec.ConcesionarioDeAuto.application.service.dto.query;

import jakarta.validation.constraints.NotBlank;

public record ConsultarClientePorIdQuery(@NotBlank(message = "id must not be blank") String clienteId)
{

}