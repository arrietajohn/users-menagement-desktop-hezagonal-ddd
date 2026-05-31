package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateRepresentanteCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateRepresentanteCommand;
import com.jcaa.usersmanagement.domain.model.RepresentanteModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateRepresentanteRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.RepresentanteResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateRepresentanteRequest;

public class RepresentanteDesktopMapper {

    public CreateRepresentanteCommand toCommand(CreateRepresentanteRequest request) {
        return new CreateRepresentanteCommand(
                request.id(),
                request.nombre(),
                request.telefono(),
                request.direccion()
        );
    }

    public UpdateRepresentanteCommand toCommand(UpdateRepresentanteRequest request) {
        return new UpdateRepresentanteCommand(
                request.id(),
                request.nombre(),
                request.telefono(),
                request.direccion()
        );
    }

    public RepresentanteResponse toResponse(RepresentanteModel model) {
        return new RepresentanteResponse(
                model.getId(),
                model.getNombre(),
                model.getTelefono(),
                model.getDireccion()
        );
    }
}