package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record TareaPersistenceDto(
    Long id,
    String titulo,
    String descripcion,
    String prioridad,
    String estado,
    String fechaVencimiento,
    Long empleadoId) {}
