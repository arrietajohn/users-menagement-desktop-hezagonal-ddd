package co.edu.udc.desechos_fabrica.user.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;

public record DeleteUserCommand(
    @NotBlank(message = "id must not be blank") String id
) {

}
