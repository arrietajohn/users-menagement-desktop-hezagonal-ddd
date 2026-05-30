package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record ResidenciaPersistenceDto(
        Integer id,
        Integer personaId,
        String municipioId,
        String paisExtranjero,
        String direccion,
        String fechaInicio) {}