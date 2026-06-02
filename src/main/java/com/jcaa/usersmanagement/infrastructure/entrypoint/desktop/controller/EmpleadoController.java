package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateEmpleadoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteEmpleadoUseCase;
import com.jcaa.usersmanagement.application.port.in.FindEmpleadoByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.ListEmpleadosUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateEmpleadoUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateEmpleadoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.EmpleadoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateEmpleadoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.EmpleadoDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class EmpleadoController {

  private final CreateEmpleadoUseCase createEmpleadoUseCase;
  private final UpdateEmpleadoUseCase updateEmpleadoUseCase;
  private final DeleteEmpleadoUseCase deleteEmpleadoUseCase;
  private final FindEmpleadoByIdUseCase findEmpleadoByIdUseCase;
  private final ListEmpleadosUseCase listEmpleadosUseCase;

  public List<EmpleadoResponse> listAllEmpleados() {
    return EmpleadoDesktopMapper.toResponseList(listEmpleadosUseCase.execute());
  }

  public EmpleadoResponse findEmpleadoById(final Long id) {
    return EmpleadoDesktopMapper.toResponse(
        findEmpleadoByIdUseCase.execute(EmpleadoDesktopMapper.toFindByIdQuery(id)));
  }

  public EmpleadoResponse createEmpleado(final CreateEmpleadoRequest request) {
    return EmpleadoDesktopMapper.toResponse(
        createEmpleadoUseCase.execute(EmpleadoDesktopMapper.toCreateCommand(request)));
  }

  public EmpleadoResponse updateEmpleado(final UpdateEmpleadoRequest request) {
    return EmpleadoDesktopMapper.toResponse(
        updateEmpleadoUseCase.execute(EmpleadoDesktopMapper.toUpdateCommand(request)));
  }

  public void deleteEmpleado(final Long id) {
    deleteEmpleadoUseCase.execute(EmpleadoDesktopMapper.toDeleteCommand(id));
  }
}
