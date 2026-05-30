package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto;

public record UpdateUserRequest(
    String currentEmail,
    String firstName,
    String lastName,
    String email,
    String password,
    String role,
    String status) {}
