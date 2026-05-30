package co.edu.udc.desechos_fabrica.user.application.service.dto.query;

import jakarta.validation.constraints.NotBlank;

public record GetUserByEmailQuery(@NotBlank(message = "email must not be blank") String email)
{

}
