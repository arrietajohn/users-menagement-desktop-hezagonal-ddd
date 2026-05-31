package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateAerolineaUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteAerolineaUseCase;
import com.jcaa.usersmanagement.application.port.in.ListAerolineasUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.AerolineaResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateAerolineaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.AerolineaDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class AerolineaController {

    private final CreateAerolineaUseCase createAerolineaUseCase;
    private final ListAerolineasUseCase listAerolineasUseCase;
    private final DeleteAerolineaUseCase deleteAerolineaUseCase;

    public List<AerolineaResponse> listAll() {
        final var aerolineas = listAerolineasUseCase.execute();
        return AerolineaDesktopMapper.toResponseList(aerolineas);
    }

    public AerolineaResponse create(CreateAerolineaRequest request) {
        final var command = AerolineaDesktopMapper.toCreateCommand(request);
        final var aerolinea = createAerolineaUseCase.execute(command);
        return AerolineaDesktopMapper.toResponse(aerolinea);
    }

    public void delete(Integer id) {
        deleteAerolineaUseCase.execute(id);
    }
}