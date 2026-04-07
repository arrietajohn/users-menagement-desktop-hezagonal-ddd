package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record UserResponse(
    String id,
    String name,
    String email,
    String role,
    String status) {}
