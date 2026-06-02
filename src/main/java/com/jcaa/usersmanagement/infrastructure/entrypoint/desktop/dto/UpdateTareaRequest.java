package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record UpdateTareaRequest(
    Long id,
    String titulo,
    String descripcion,
    String prioridad,
    String estado,
    String fechaVencimiento,
    Long empleadoId) {}
