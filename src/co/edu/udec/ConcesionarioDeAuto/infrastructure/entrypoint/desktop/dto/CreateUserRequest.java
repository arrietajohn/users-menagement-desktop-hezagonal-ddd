package edu.udec.ConcesionarioDeAuto.infrastructure.entrypoint.desktop.dto;

public record CreateUserRequest(
    String id,
    String name,
    String email,
    String password,
    String role) {}
