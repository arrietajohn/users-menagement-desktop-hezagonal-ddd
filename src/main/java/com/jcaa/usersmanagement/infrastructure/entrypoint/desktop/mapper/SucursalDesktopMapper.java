package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateSucursalCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteSucursalCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateSucursalCommand;
import com.jcaa.usersmanagement.domain.model.SucursalModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateSucursalRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.SucursalResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateSucursalRequest;

import java.util.List;

public final class SucursalDesktopMapper {

    private SucursalDesktopMapper() {}

    public static CreateSucursalCommand toCreateCommand(final CreateSucursalRequest request) {
        return new CreateSucursalCommand(
                request.id(),
                request.numero(),
                request.direccion(),
                request.codigoPostal(),
                request.ciudad(),
                request.bancoId()
        );
    }

    public static UpdateSucursalCommand toUpdateCommand(final UpdateSucursalRequest request) {
        return new UpdateSucursalCommand(
                request.id(),
                request.numero(),
                request.direccion(),
                request.codigoPostal(),
                request.ciudad(),
                request.bancoId()
        );
    }

    public static DeleteSucursalCommand toDeleteCommand(final String id) {
        return new DeleteSucursalCommand(id);
    }

    public static SucursalResponse toResponse(final SucursalModel sucursal) {
        return new SucursalResponse(
                sucursal.getId().value(),
                sucursal.getNumero().value(),
                sucursal.getDireccion().value(),
                sucursal.getCodigoPostal().value(),
                sucursal.getCiudad().value(),
                sucursal.getBancoId().value()
        );
    }

    public static List<SucursalResponse> toResponseList(final List<SucursalModel> sucursales) {
        return sucursales.stream().map(SucursalDesktopMapper::toResponse).toList();
    }
}