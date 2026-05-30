package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateSessionCommand;
import com.jcaa.usersmanagement.application.service.dto.command.CreateUserCommand;
import com.jcaa.usersmanagement.domain.model.Session;
import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateSessionRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.SessionResponse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public final class SessionDesktopMapper {

    private SessionDesktopMapper() {
    }

    public static CreateSessionCommand toCreateCommand(final CreateSessionRequest request) {
        if (request == null) {
            return null;
        }

        return new CreateSessionCommand(
                request.id(),
                request.salaId(),
                request.investigacionId(),
                request.ponenteID(),
                request.chairmaId(),
                request.fecha() != null ? LocalDate.parse(request.fecha()) : null,
                request.horaInicio() != null ? LocalTime.parse(request.horaInicio()) : null,
                request.horaFin() != null ? LocalTime.parse(request.horaFin()) : null
        );
    }

    public static SessionResponse toResponse(final Session session) {
        if (session == null) {
            return null;
        }

        return new SessionResponse(
                session.getId().value(),
                session.getSalaID().value(),
                session.getInvestigacionId().value(),
                session.getPonenteId().value(),
                session.getChairmanId().value(),
                session.getFecha() != null ? session.getFecha().toString() : null,
                session.getHoraInicio() != null ? session.getHoraInicio().toString() : null,
                session.getHoraFin() != null ? session.getHoraFin().toString() : null
        );
    }
}