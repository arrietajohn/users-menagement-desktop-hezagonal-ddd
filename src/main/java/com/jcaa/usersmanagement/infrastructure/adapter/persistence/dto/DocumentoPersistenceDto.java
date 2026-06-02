package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record DocumentoPersistenceDto(
    Long id,
    String titulo,
    String tipo,
    String contenido,
    String fechaCreacion,
    String estado,
    Long autorId) {}
