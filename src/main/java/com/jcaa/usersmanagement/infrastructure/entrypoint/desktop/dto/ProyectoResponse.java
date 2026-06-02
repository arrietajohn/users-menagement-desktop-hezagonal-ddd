package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record ProyectoResponse(
    Long id,
    String nombreClave,
    String denominacion,
    String fechaInicio,
    String fechaFin,
    String estado,
    Long promotorId) {}
