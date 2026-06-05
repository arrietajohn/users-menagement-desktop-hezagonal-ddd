package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllRoutesUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllRoutesPort;
import com.jcaa.usersmanagement.domain.model.RouteModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class GetAllRoutesService implements GetAllRoutesUseCase {

    private final GetAllRoutesPort getAllRoutesPort;

    @Override
    public List<RouteModel> execute() {
        return getAllRoutesPort.getAll();
    }
}