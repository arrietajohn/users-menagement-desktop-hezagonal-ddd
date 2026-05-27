package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateRangoMilitarCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteRangoMilitarCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateRangoMilitarCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetRangoMilitarByIdQuery;
import com.jcaa.usersmanagement.domain.model.RangoMilitarModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateRangoMilitarRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.RangoMilitarResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateRangoMilitarRequest;

import java.util.List;

public final class RangoMilitarDesktopMapper {

    private RangoMilitarDesktopMapper() {}

    public static CreateRangoMilitarCommand toCreateCommand(
            final CreateRangoMilitarRequest request) {
        return new CreateRangoMilitarCommand(
                request.id(),
                request.codigo(),
                request.nombre(),
                request.descripcion(),
                request.lineaMilitar(),
                request.tiempoMinimoAscensoMeses());
    }

    public static UpdateRangoMilitarCommand toUpdateCommand(
            final UpdateRangoMilitarRequest request) {
        return new UpdateRangoMilitarCommand(
                request.id(),
                request.codigo(),
                request.nombre(),
                request.descripcion(),
                request.lineaMilitar(),
                request.tiempoMinimoAscensoMeses());
    }

    public static DeleteRangoMilitarCommand toDeleteCommand(final String id) {
        return new DeleteRangoMilitarCommand(id);
    }

    public static GetRangoMilitarByIdQuery toGetByIdQuery(final String id) {
        return new GetRangoMilitarByIdQuery(id);
    }

    public static RangoMilitarResponse toResponse(final RangoMilitarModel rango) {
        return new RangoMilitarResponse(
                rango.getId().value(),
                rango.getCodigo().value(),
                rango.getNombre().value(),
                rango.getDescripcion().value(),
                rango.getLineaMilitar().name(),
                rango.getTiempoMinimoAscenso().meses());
    }

    public static List<RangoMilitarResponse> toResponseList(final List<RangoMilitarModel> rangos) {
        return rangos.stream().map(RangoMilitarDesktopMapper::toResponse).toList();
    }
}
