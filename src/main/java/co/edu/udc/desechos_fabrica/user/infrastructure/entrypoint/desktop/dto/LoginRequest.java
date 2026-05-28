package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto;

public record LoginRequest(
    String email,
    String password) {}
