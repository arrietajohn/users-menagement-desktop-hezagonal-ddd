package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record EspecieResponseDto(
    String id,
    String nombreCientifico,
    String nombreEspañol,
    String descripcion
) {}
