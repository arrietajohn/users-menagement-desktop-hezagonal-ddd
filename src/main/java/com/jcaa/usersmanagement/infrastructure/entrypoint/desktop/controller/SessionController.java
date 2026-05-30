package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateSessionUseCase;
import com.jcaa.usersmanagement.application.port.in.CreateUserUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllSessionUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllUsersUseCase;
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



    public SessionResponse createSession(final CreateSessionRequest request) {
        final var command = SessionDesktopMapper.toCreateCommand(request);
        final var session= createSessionUseCase.execute(command);
        return SessionDesktopMapper.toResponse(session);
    }

    public List<SessionResponse> listAllSession() {
        final var sesions = getAllSessionUseCase.execute();
        return SessionDesktopMapper.toResponseList(sesions);
    }
}
