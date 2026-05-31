package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateProyectoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteProyectoUseCase;
import com.jcaa.usersmanagement.application.port.in.FindProyectoByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.ListProyectosUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateProyectoUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateProyectoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProyectoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateProyectoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.ProyectoDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class ProyectoController {

  private final CreateProyectoUseCase createProyectoUseCase;
  private final UpdateProyectoUseCase updateProyectoUseCase;
  private final DeleteProyectoUseCase deleteProyectoUseCase;
  private final FindProyectoByIdUseCase findProyectoByIdUseCase;
  private final ListProyectosUseCase listProyectosUseCase;

  public List<ProyectoResponse> listAllProyectos() {
    final var proyectos = listProyectosUseCase.execute();
    return ProyectoDesktopMapper.toResponseList(proyectos);
  }

  public ProyectoResponse findProyectoById(final Long id) {
    final var query = ProyectoDesktopMapper.toFindByIdQuery(id);
    final var proyecto = findProyectoByIdUseCase.execute(query);
    return ProyectoDesktopMapper.toResponse(proyecto);
  }

  public ProyectoResponse createProyecto(final CreateProyectoRequest request) {
    final var command = ProyectoDesktopMapper.toCreateCommand(request);
    final var proyecto = createProyectoUseCase.execute(command);
    return ProyectoDesktopMapper.toResponse(proyecto);
  }

  public ProyectoResponse updateProyecto(final UpdateProyectoRequest request) {
    final var command = ProyectoDesktopMapper.toUpdateCommand(request);
    final var proyecto = updateProyectoUseCase.execute(command);
    return ProyectoDesktopMapper.toResponse(proyecto);
  }

  public void deleteProyecto(final Long id) {
    final var command = ProyectoDesktopMapper.toDeleteCommand(id);
    deleteProyectoUseCase.execute(command);
  }
}
