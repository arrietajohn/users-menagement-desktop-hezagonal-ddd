package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto;

public record UpdateUserRequest(
    String actorEmail,
    String targetEmail,
    String newFirstName,
    String newLastName,
    String newEmail,
    String password,
    String role,
    String status,
    String nit) {}
