package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateTareaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteTareaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateTareaCommand;
import com.jcaa.usersmanagement.application.service.dto.query.FindTareaByIdQuery;
import com.jcaa.usersmanagement.domain.model.TareaModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateTareaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.TareaResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateTareaRequest;

import java.util.List;

public final class TareaDesktopMapper {

  private TareaDesktopMapper() {}

  public static CreateTareaCommand toCreateCommand(final CreateTareaRequest request) {
    return new CreateTareaCommand(
        request.titulo(), request.descripcion(), request.prioridad(),
        request.estado(), request.fechaVencimiento(), request.empleadoId());
  }

  public static UpdateTareaCommand toUpdateCommand(final UpdateTareaRequest request) {
    return new UpdateTareaCommand(
        request.id(), request.titulo(), request.descripcion(), request.prioridad(),
        request.estado(), request.fechaVencimiento(), request.empleadoId());
  }

  public static DeleteTareaCommand toDeleteCommand(final Long id) {
    return new DeleteTareaCommand(id);
  }

  public static FindTareaByIdQuery toFindByIdQuery(final Long id) {
    return new FindTareaByIdQuery(id);
  }

  public static TareaResponse toResponse(final TareaModel tarea) {
    return new TareaResponse(
        tarea.getId().value(),
        tarea.getTitulo(),
        tarea.getDescripcion(),
        tarea.getPrioridad().name(),
        tarea.getEstado().name(),
        tarea.getFechaVencimiento() != null ? tarea.getFechaVencimiento().toString() : null,
        tarea.getEmpleadoId().value());
  }

  public static List<TareaResponse> toResponseList(final List<TareaModel> tareas) {
    return tareas.stream().map(TareaDesktopMapper::toResponse).toList();
  }
}
