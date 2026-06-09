package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetSessionsByDateUseCase;
import com.jcaa.usersmanagement.application.port.out.GetSessionByDatePort;
import com.jcaa.usersmanagement.domain.model.Session;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public final class GetSessionByDateService implements GetSessionsByDateUseCase {

    private final GetSessionByDatePort getSessionByDatePort;

    @Override
    public List<Session> execute(String fecha){
        return getSessionByDatePort.getByDate(fecha);
    }
}
