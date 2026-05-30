package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

public record ResidenciaEntity(
        Integer id,
        Integer personaId,
        String municipioId,
        String paisExtranjero,
        String direccion,
        String fechaInicio) {}