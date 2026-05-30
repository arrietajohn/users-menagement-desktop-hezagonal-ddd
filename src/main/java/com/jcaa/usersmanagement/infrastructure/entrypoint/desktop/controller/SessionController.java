package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.*;
import com.jcaa.usersmanagement.domain.model.Session;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateSessionRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateUserRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.SessionResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UserResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.SessionDesktopMapper;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.UserDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class SessionController {

    private final CreateSessionUseCase createSessionUseCase;
    private final GetAllSessionUseCase getAllSessionUseCase;
    private final GetSessionByIdUseCase getSessionByIdUseCase;



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
}
