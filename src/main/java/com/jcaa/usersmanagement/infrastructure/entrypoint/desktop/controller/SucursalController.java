package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateSucursalUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteSucursalUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateSucursalUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateSucursalRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.SucursalResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateSucursalRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.SucursalDesktopMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class SucursalController {

    private final CreateSucursalUseCase createSucursalUseCase;
    private final UpdateSucursalUseCase updateSucursalUseCase;
    private final DeleteSucursalUseCase deleteSucursalUseCase;

    public SucursalResponse createSucursal(final CreateSucursalRequest request) {
        final var command = SucursalDesktopMapper.toCreateCommand(request);
        final var sucursal = createSucursalUseCase.execute(command);
        return SucursalDesktopMapper.toResponse(sucursal);
    }

    public SucursalResponse updateSucursal(final UpdateSucursalRequest request) {
        final var command = SucursalDesktopMapper.toUpdateCommand(request);
        final var sucursal = updateSucursalUseCase.execute(command);
        return SucursalDesktopMapper.toResponse(sucursal);
    }

    public void deleteSucursal(final String id) {
        final var command = SucursalDesktopMapper.toDeleteCommand(id);
        deleteSucursalUseCase.execute(command);
    }
}