package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

public record TareaEntity(
    Long id,
    String titulo,
    String descripcion,
    String prioridad,
    String estado,
    String fechaVencimiento,
    Long empleadoId,
    String createdAt,
    String updatedAt) {}
