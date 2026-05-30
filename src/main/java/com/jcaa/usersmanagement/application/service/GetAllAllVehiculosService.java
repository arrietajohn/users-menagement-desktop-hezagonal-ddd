package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllVehiculosUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllVehiculosPort;
import com.jcaa.usersmanagement.domain.model.Vehiculomodel;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public final class GetAllAllVehiculosService implements GetAllVehiculosUseCase {

    private final GetAllVehiculosPort getAllVehiculosPort;

    @Override
    public List<Vehiculomodel> execute() {
        return getAllVehiculosPort.getAll();
    }
}
