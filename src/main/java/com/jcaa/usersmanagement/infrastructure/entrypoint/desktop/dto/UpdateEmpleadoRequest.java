package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record UpdateEmpleadoRequest(
    Long id,
    String nombre,
    String apellido,
    String email,
    String cargo,
    String fechaContratacion,
    String estado) {}
