package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record UpdateResidenciaRequest(
        Integer id,
        Integer personaId,
        String municipioId,
        String paisExtranjero,
        String direccion,
        String fechaInicio) {}