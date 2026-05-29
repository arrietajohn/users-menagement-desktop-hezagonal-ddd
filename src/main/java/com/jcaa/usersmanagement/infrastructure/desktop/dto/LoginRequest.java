package com.jcaa.usersmanagement.infrastructure.desktop.dto;

public record LoginRequest(
    String email,
    String password) {}
