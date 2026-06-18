package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetSessionByDateAndSalaUseCase;
import com.jcaa.usersmanagement.application.port.out.GetSessionByDateAndSalaPort;
import com.jcaa.usersmanagement.application.port.out.GetSessionOrderedByDatePort;
import com.jcaa.usersmanagement.domain.model.Session;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class GetSessionByDateAndSalaService implements GetSessionByDateAndSalaUseCase {

    private final GetSessionByDateAndSalaPort getSessionByDateAndSalaPort;

    @Override
    public List<Session> execute(String fecha, String salaId){
        return getSessionByDateAndSalaPort.getByDateAndSala (fecha, salaId);
    }
}
