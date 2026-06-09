package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetSessionOrderedByDateUseCase;
import com.jcaa.usersmanagement.application.port.out.GetSessionOrderedByDatePort;
import com.jcaa.usersmanagement.domain.model.Session;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class GetSessionOrderedByDateService implements GetSessionOrderedByDateUseCase {

    private final GetSessionOrderedByDatePort getSessionOrderedByDatePort;

    @Override
    public List<Session> execute() {
        return getSessionOrderedByDatePort.getOrderedByDate();
    }
}
