package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateResidenciaUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteResidenciaUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllResidenciasUseCase;
import com.jcaa.usersmanagement.application.port.in.GetResidenciaByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateResidenciaUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateResidenciaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ResidenciaResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateResidenciaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.ResidenciaDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class ResidenciaController {

    private final CreateResidenciaUseCase createResidenciaUseCase;
    private final UpdateResidenciaUseCase updateResidenciaUseCase;
    private final DeleteResidenciaUseCase deleteResidenciaUseCase;
    private final GetResidenciaByIdUseCase getResidenciaByIdUseCase;
    private final GetAllResidenciasUseCase getAllResidenciasUseCase;

    public ResidenciaResponse createResidencia(final CreateResidenciaRequest request) {
        final var command = ResidenciaDesktopMapper.toCreateCommand(request);
        final var model = createResidenciaUseCase.execute(command);
        return ResidenciaDesktopMapper.toResponse(model);
    }

    public ResidenciaResponse updateResidencia(final UpdateResidenciaRequest request) {
        final var command = ResidenciaDesktopMapper.toUpdateCommand(request);
        final var model = updateResidenciaUseCase.execute(command);
        return ResidenciaDesktopMapper.toResponse(model);
    }

    public void deleteResidencia(final Integer id) {
        final var command = ResidenciaDesktopMapper.toDeleteCommand(id);
        deleteResidenciaUseCase.execute(command);
    }

    public ResidenciaResponse findResidenciaById(final Integer id) {
        final var query = ResidenciaDesktopMapper.toGetByIdQuery(id);
        final var model = getResidenciaByIdUseCase.execute(query);
        return ResidenciaDesktopMapper.toResponse(model);
    }

    public List<ResidenciaResponse> listAllResidencias() {
        final var models = getAllResidenciasUseCase.execute();
        return ResidenciaDesktopMapper.toResponseList(models);
    }
}