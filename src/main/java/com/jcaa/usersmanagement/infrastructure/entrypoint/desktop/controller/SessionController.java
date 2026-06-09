package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.*;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateSessionRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.SessionResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.SessionDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class SessionController {

    private final CreateSessionUseCase createSessionUseCase;
    private final GetAllSessionUseCase getAllSessionUseCase;
    private final GetSessionByIdUseCase getSessionByIdUseCase;
    private final GetSessionsByDateUseCase getSessionsByDateUseCase;
    private final GetSessionByChairmanUseCase getSessionBychairmanUseCase;



    public SessionResponse createSession(final CreateSessionRequest request) {
        final var command = SessionDesktopMapper.toCreateCommand(request);
        final var session= createSessionUseCase.execute(command);
        return SessionDesktopMapper.toResponse(session);
    }

    public List<SessionResponse> listAllSession() {
        final var sesions = getAllSessionUseCase.execute();
        return SessionDesktopMapper.toResponseList(sesions);
    }

    public SessionResponse findSessionById(final String id) {
        final var query = SessionDesktopMapper.toGetByIdQuery(id);
        final var session = getSessionByIdUseCase.execute(query);
        return SessionDesktopMapper.toResponse(session);
    }

    public List<SessionResponse> getSessionByDate(final String fecha) {
        final var sessions = getSessionsByDateUseCase.execute(fecha);
        return SessionDesktopMapper.toResponseList(sessions);
    }

    public List<SessionResponse> getSessionByChairman(final String chairman) {
        final var sessions = getSessionBychairmanUseCase.execute(chairman);
        return SessionDesktopMapper.toResponseList(sessions);
    }
}
