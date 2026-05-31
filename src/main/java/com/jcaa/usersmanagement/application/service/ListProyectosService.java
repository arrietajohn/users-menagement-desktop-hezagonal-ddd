package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.ListProyectosUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllProyectosPort;
import com.jcaa.usersmanagement.domain.model.ProyectoModel;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public final class ListProyectosService implements ListProyectosUseCase {

  private final GetAllProyectosPort getAllProyectosPort;

  @Override
  public List<ProyectoModel> execute() {
    final List<ProyectoModel> proyectos = getAllProyectosPort.getAll();
    if (proyectos.isEmpty()) {
      return Collections.emptyList();
    }
    return proyectos;
  }
}
