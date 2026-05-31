package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record CreateProyectoRequest(
    String nombreClave,
    String denominacion,
    String fechaInicio,
    String fechaFin,
    String estado,
    Long promotorId) {}
