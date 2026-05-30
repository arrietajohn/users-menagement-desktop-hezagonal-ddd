package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto;

public record CreateUserRequest(
    String firstName,
    String lastName,
    String email,
    String password,
    String role) {}
