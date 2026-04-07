package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record LoginRequest(
    String email,
    String password) {}
