package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateSessionRequest(
        String id,
        String salaId,
        String investigacionId,
        String ponenteID,
        String chairmaId,
        String fecha,
        String horaInicio,
        String horaFin) {}
