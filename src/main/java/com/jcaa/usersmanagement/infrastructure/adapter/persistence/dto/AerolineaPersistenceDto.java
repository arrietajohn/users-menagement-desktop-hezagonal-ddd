package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record AerolineaPersistenceDto(
        Integer idAerolinea,
        String nombre,
        String paisOrigen
) {
}