package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record EspeciePersistenceDto(
    String id,
    String nombreCientifico,
    String nombreEspañol,
    String descripcion,
    String createdAt,
    String updatedAt
) {}
