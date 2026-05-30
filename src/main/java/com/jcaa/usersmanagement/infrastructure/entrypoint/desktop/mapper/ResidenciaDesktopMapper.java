package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateResidenciaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteResidenciaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateResidenciaCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetResidenciaByIdQuery;
import com.jcaa.usersmanagement.domain.model.ResidenciaModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateResidenciaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ResidenciaResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateResidenciaRequest;

import java.time.LocalDate;
import java.util.List;

public final class ResidenciaDesktopMapper {

    private ResidenciaDesktopMapper() {}

    public static CreateResidenciaCommand toCreateCommand(final CreateResidenciaRequest request) {
        return new CreateResidenciaCommand(
                request.personaId(),
                request.municipioId(),
                request.paisExtranjero(),
                request.direccion(),
                LocalDate.parse(request.fechaInicio()));
    }

    public static UpdateResidenciaCommand toUpdateCommand(final UpdateResidenciaRequest request) {
        return new UpdateResidenciaCommand(
                request.id(),
                request.personaId(),
                request.municipioId(),
                request.paisExtranjero(),
                request.direccion(),
                LocalDate.parse(request.fechaInicio()));
    }

    public static DeleteResidenciaCommand toDeleteCommand(final Integer id) {
        return new DeleteResidenciaCommand(id);
    }

    public static GetResidenciaByIdQuery toGetByIdQuery(final Integer id) {
        return new GetResidenciaByIdQuery(id);
    }

    public static ResidenciaResponse toResponse(final ResidenciaModel model) {
        return new ResidenciaResponse(
                model.getId().value(),
                model.getPersonaId().value(),
                model.getMunicipioId().value(),
                model.getPaisExtranjero() != null ? model.getPaisExtranjero().value() : null,
                model.getDireccion() != null ? model.getDireccion().value() : null,
                model.getFechaInicio() != null ? model.getFechaInicio().toString() : null);
    }

    public static List<ResidenciaResponse> toResponseList(final List<ResidenciaModel> models) {
        return models.stream().map(ResidenciaDesktopMapper::toResponse).toList();
    }
}