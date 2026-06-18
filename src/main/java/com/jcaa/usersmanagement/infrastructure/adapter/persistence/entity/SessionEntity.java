package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

public record SessionEntity(
        String id,
        String salaId,
        String investigacionId,
        String ponenteId,
        String chairmanId,
        String fecha,
        String horaInicio,
        String horaFin) {}
