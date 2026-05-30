package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto;

public record UserResponse(
    String firstName,
    String lastName,
    String email,
    String role,
    String status) {}
