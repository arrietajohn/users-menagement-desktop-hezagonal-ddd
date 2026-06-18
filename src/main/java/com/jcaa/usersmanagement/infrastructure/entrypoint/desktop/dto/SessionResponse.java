package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

import java.time.LocalDate;

public record SessionResponse(
        String id,
        String salaId,
        String investigacionId,
        String ponenteId,
        String ChairmanId,
        String fecha,
        String horaInicio,
        String horaFin
) {}