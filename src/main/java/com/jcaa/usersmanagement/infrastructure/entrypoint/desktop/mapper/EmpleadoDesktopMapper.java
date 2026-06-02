package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateEmpleadoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteEmpleadoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateEmpleadoCommand;
import com.jcaa.usersmanagement.application.service.dto.query.FindEmpleadoByIdQuery;
import com.jcaa.usersmanagement.domain.model.EmpleadoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateEmpleadoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.EmpleadoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateEmpleadoRequest;

import java.util.List;

public final class EmpleadoDesktopMapper {

  private EmpleadoDesktopMapper() {}

  public static CreateEmpleadoCommand toCreateCommand(final CreateEmpleadoRequest request) {
    return new CreateEmpleadoCommand(
        request.nombre(), request.apellido(), request.email(),
        request.cargo(), request.fechaContratacion(), request.estado());
  }

  public static UpdateEmpleadoCommand toUpdateCommand(final UpdateEmpleadoRequest request) {
    return new UpdateEmpleadoCommand(
        request.id(), request.nombre(), request.apellido(), request.email(),
        request.cargo(), request.fechaContratacion(), request.estado());
  }

  public static DeleteEmpleadoCommand toDeleteCommand(final Long id) {
    return new DeleteEmpleadoCommand(id);
  }

  public static FindEmpleadoByIdQuery toFindByIdQuery(final Long id) {
    return new FindEmpleadoByIdQuery(id);
  }

  public static EmpleadoResponse toResponse(final EmpleadoModel empleado) {
    return new EmpleadoResponse(
        empleado.getId().value(),
        empleado.getNombre(),
        empleado.getApellido(),
        empleado.getEmail(),
        empleado.getCargo(),
        empleado.getFechaContratacion().toString(),
        empleado.getEstado().name());
  }

  public static List<EmpleadoResponse> toResponseList(final List<EmpleadoModel> empleados) {
    return empleados.stream().map(EmpleadoDesktopMapper::toResponse).toList();
  }
}
