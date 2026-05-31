package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateProyectoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteProyectoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateProyectoCommand;
import com.jcaa.usersmanagement.application.service.dto.query.FindProyectoByIdQuery;
import com.jcaa.usersmanagement.domain.model.ProyectoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateProyectoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProyectoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateProyectoRequest;

import java.util.List;

public final class ProyectoDesktopMapper {

  private ProyectoDesktopMapper() {}

  public static CreateProyectoCommand toCreateCommand(final CreateProyectoRequest request) {
    return new CreateProyectoCommand(
        request.nombreClave(),
        request.denominacion(),
        request.fechaInicio(),
        request.fechaFin(),
        request.estado(),
        request.promotorId());
  }

  public static UpdateProyectoCommand toUpdateCommand(final UpdateProyectoRequest request) {
    return new UpdateProyectoCommand(
        request.id(),
        request.nombreClave(),
        request.denominacion(),
        request.fechaInicio(),
        request.fechaFin(),
        request.estado(),
        request.promotorId());
  }

  public static DeleteProyectoCommand toDeleteCommand(final Long id) {
    return new DeleteProyectoCommand(id);
  }

  public static FindProyectoByIdQuery toFindByIdQuery(final Long id) {
    return new FindProyectoByIdQuery(id);
  }

  public static ProyectoResponse toResponse(final ProyectoModel proyecto) {
    return new ProyectoResponse(
        proyecto.getId().value(),
        proyecto.getNombreClave(),
        proyecto.getDenominacion(),
        proyecto.getFechaInicio().toString(),
        proyecto.getFechaFin() != null ? proyecto.getFechaFin().toString() : null,
        proyecto.getEstado().name(),
        proyecto.getPromotorId());
  }

  public static List<ProyectoResponse> toResponseList(final List<ProyectoModel> proyectos) {
    return proyectos.stream().map(ProyectoDesktopMapper::toResponse).toList();
  }
}
