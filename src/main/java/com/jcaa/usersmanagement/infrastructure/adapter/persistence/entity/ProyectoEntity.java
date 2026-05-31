package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

public record ProyectoEntity(
    Long id,
    String nombreClave,
    String denominacion,
    String fechaInicio,
    String fechaFin,
    String estado,
    Long promotorId,
    String createdAt,
    String updatedAt) {}
