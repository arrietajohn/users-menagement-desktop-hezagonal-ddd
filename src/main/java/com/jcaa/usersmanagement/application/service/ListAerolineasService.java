package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.ListAerolineasUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllAerolineaPort;
import com.jcaa.usersmanagement.domain.model.AerolineaModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListAerolineasService implements ListAerolineasUseCase {

    private final GetAllAerolineaPort getAllAerolineasPort;

    @Override
    public List<AerolineaModel> execute() {
        return getAllAerolineasPort.getAll();
    }
}