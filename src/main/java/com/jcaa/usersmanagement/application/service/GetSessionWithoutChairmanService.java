package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetSessionWithoutChairmanUseCase;
import com.jcaa.usersmanagement.application.port.out.GetSessionWithoutChairmanPort;
import com.jcaa.usersmanagement.domain.model.Session;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class GetSessionWithoutChairmanService implements GetSessionWithoutChairmanUseCase {

    private final GetSessionWithoutChairmanPort getSessionWithoutChairmanPort;

    @Override
    public List<Session> execute() {
        return getSessionWithoutChairmanPort.getWithoutChairman();
    }
}