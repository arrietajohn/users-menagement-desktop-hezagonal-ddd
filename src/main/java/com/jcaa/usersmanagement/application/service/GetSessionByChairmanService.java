package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetSessionByChairmanUseCase;
import com.jcaa.usersmanagement.application.port.out.GetSessionByChairmanPort;
import com.jcaa.usersmanagement.domain.model.Session;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class GetSessionByChairmanService implements GetSessionByChairmanUseCase {

    private final GetSessionByChairmanPort getSessionByChairmanPort;

    @Override
    public List<Session>execute(String chairmanId) {
        return getSessionByChairmanPort.getByChairman(chairmanId);
    }
}
