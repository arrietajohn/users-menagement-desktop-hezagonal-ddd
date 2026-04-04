package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserCommand(
    @NotBlank(message = "id must not be blank") String id,
    @NotBlank(message = "name must not be blank")
        @Size(min = 3, message = "name must have at least 3 characters")
        String name,
    @NotBlank(message = "email must not be blank")
        @Email(message = "email must be a valid email address")
        String email,
    String password,
    @NotBlank(message = "role must not be blank") String role,
    @NotBlank(message = "status must not be blank") String status)
{

}
