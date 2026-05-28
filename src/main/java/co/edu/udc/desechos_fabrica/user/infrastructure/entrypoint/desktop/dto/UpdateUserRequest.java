package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto;

public record UpdateUserRequest(
    String id,
    String name,
    String email,
    String password,
    String role,
    String status) {}
