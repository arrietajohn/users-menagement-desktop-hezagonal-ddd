package co.edu.udc.desechos_fabrica.user.application.service.dto.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserCommand(
    @NotBlank(message = "first name must not be blank")
        @Size(min = 3, message = "first name must have at least 3 characters")
        String firstName,
    @NotBlank(message = "last name must not be blank")
        @Size(min = 3, message = "last name must have at least 3 characters")
        String lastName,
    @NotBlank(message = "email must not be blank")
        @Email(message = "email must be a valid email address")
        String email,
    @NotBlank(message = "password must not be blank")
        @Size(min = 8, message = "password must have at least 8 characters")
        String password,
    @NotBlank(message = "role must not be blank") String role)
{

}
