package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateRangoMilitarUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteRangoMilitarUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllRangosMilitaresUseCase;
import com.jcaa.usersmanagement.application.port.in.GetRangoMilitarByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateRangoMilitarUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateRangoMilitarRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.RangoMilitarResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateRangoMilitarRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.RangoMilitarDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class RangoMilitarController {

    private final CreateRangoMilitarUseCase createRangoMilitarUseCase;
    private final UpdateRangoMilitarUseCase updateRangoMilitarUseCase;
    private final DeleteRangoMilitarUseCase deleteRangoMilitarUseCase;
    private final GetRangoMilitarByIdUseCase getRangoMilitarByIdUseCase;
    private final GetAllRangosMilitaresUseCase getAllRangosMilitaresUseCase;

    public List<RangoMilitarResponse> listAllRangos() {
        return RangoMilitarDesktopMapper.toResponseList(getAllRangosMilitaresUseCase.execute());
    }

    public RangoMilitarResponse findRangoById(final String id) {
        final var query = RangoMilitarDesktopMapper.toGetByIdQuery(id);
        return RangoMilitarDesktopMapper.toResponse(getRangoMilitarByIdUseCase.execute(query));
    }

    public RangoMilitarResponse createRango(final CreateRangoMilitarRequest request) {
        final var command = RangoMilitarDesktopMapper.toCreateCommand(request);
        return RangoMilitarDesktopMapper.toResponse(createRangoMilitarUseCase.execute(command));
    }

    public RangoMilitarResponse updateRango(final UpdateRangoMilitarRequest request) {
        final var command = RangoMilitarDesktopMapper.toUpdateCommand(request);
        return RangoMilitarDesktopMapper.toResponse(updateRangoMilitarUseCase.execute(command));
    }

    public void deleteRango(final String id) {
        final var command = RangoMilitarDesktopMapper.toDeleteCommand(id);
        deleteRangoMilitarUseCase.execute(command);
    }
}
