package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto;

import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;

public record CreateUserRequest(
    String firstName,
    String lastName,
    String email,
    String password,
    UserRole role) {}
