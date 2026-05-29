package com.jcaa.usersmanagement.infrastructure.desktop.dto;

public record UpdateUserRequest(
    String id,
    String name,
    String email,
    String password,
    String role,
    String status) {}
