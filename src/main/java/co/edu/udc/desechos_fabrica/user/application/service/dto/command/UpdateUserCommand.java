package co.edu.udc.desechos_fabrica.user.application.service.dto.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserCommand(
    @NotBlank(message = "actorEmail must not be blank")
        @Email(message = "actorEmail must be a valid email address")
        String actorEmail,
    @NotBlank(message = "targetEmail must not be blank")
        @Email(message = "targetEmail must be a valid email address")
        String targetEmail,
    @NotBlank(message = "first name must not be blank")
        @Size(min = 3, message = "first name must have at least 3 characters")
        String newFirstName,
    @NotBlank(message = "last name must not be blank")
        @Size(min = 3, message = "last name must have at least 3 characters")
        String newLastName,
    @NotBlank(message = "newEmail must not be blank")
        @Email(message = "newEmail must be a valid email address")
        String newEmail,
    String password,
    @NotBlank(message = "role must not be blank") String role,
    @NotBlank(message = "status must not be blank") String status,
    String nit)
{

}
