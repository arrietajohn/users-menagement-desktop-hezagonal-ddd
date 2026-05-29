package com.jcaa.usersmanagement.infrastructure.desktop.dto;

public record CreateUserRequest(
    String id,
    String name,
    String email,
    String password,
    String role) {}
