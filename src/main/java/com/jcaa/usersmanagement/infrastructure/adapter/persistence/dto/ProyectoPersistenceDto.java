package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record ProyectoPersistenceDto(
    Long id,
    String nombreClave,
    String denominacion,
    String fechaInicio,
    String fechaFin,
    String estado,
    Long promotorId) {}
