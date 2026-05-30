package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record SessionPersistenceDto(
        String id,
        String salaId,
        String investigacionId,
        String ponenteId,
        String chairmanId,
        String fecha,
        String horaInicio,
        String horaFin
) {}
