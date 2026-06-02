package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record DocumentoResponse(
    Long id,
    String titulo,
    String tipo,
    String contenido,
    String fechaCreacion,
    String estado,
    Long autorId) {}
