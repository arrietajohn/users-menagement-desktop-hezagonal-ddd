package edu.udec.ConcesionarioDeAuto.infrastructure.entrypoint.desktop.dto;

public record LoginRequest(
    String email,
    String password) {}
