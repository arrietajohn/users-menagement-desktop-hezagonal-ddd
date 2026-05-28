package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto;

public record UserResponse(
    String id,
    String name,
    String email,
    String role,
    String status) {}
