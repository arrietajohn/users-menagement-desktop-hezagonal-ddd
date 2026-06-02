package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

public record DocumentoEntity(
    Long id,
    String titulo,
    String tipo,
    String contenido,
    String fechaCreacion,
    String estado,
    Long autorId,
    String createdAt,
    String updatedAt) {}
