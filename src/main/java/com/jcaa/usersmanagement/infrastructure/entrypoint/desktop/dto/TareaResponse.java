package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record TareaResponse(
    Long id,
    String titulo,
    String descripcion,
    String prioridad,
    String estado,
    String fechaVencimiento,
    Long empleadoId) {}
