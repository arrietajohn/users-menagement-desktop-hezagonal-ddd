package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record CreateResidenciaRequest(
        Integer personaId,
        String municipioId,
        String paisExtranjero,
        String direccion,
        String fechaInicio) {}