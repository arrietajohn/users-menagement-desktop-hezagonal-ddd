package co.edu.udc.desechos_fabrica.user.application.service.dto.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserCommand(
    @NotBlank(message = "currentEmail must not be blank")
        @Email(message = "currentEmail must be a valid email address")
        String currentEmail,
    @NotBlank(message = "first name must not be blank")
        @Size(min = 3, message = "first name must have at least 3 characters")
        String firstName,
    @NotBlank(message = "last name must not be blank")
        @Size(min = 3, message = "last name must have at least 3 characters")
        String lastName,
    @NotBlank(message = "email must not be blank")
        @Email(message = "email must be a valid email address")
        String email,
    String password,
    @NotBlank(message = "role must not be blank") String role,
    @NotBlank(message = "status must not be blank") String status)
{

}
