package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateAerolineaCommand;
import com.jcaa.usersmanagement.domain.model.AerolineaModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.AerolineaResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateAerolineaRequest;

import java.util.List;

public final class AerolineaDesktopMapper {

  private AerolineaDesktopMapper() {}

  public static CreateAerolineaCommand toCreateCommand(final CreateAerolineaRequest request) {
    return new CreateAerolineaCommand(
        null,
        request.nombre(),
        request.paisOrigen()
    );
  }

  public static AerolineaResponse toResponse(final AerolineaModel aerolinea) {
    return new AerolineaResponse(
        aerolinea.getIdAerolinea(),
        aerolinea.getNombre(),
        aerolinea.getPaisOrigen()
    );
  }

  public static List<AerolineaResponse> toResponseList(final List<AerolineaModel> aerolineas) {
    return aerolineas.stream().map(AerolineaDesktopMapper::toResponse).toList();
  }
}
