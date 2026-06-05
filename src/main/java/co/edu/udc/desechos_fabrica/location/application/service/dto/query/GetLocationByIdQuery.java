package co.edu.udc.desechos_fabrica.location.application.service.dto.query;

import jakarta.validation.constraints.NotBlank;

public record GetLocationByIdQuery(@NotBlank(message = "id must not be blank") Long id){
}
