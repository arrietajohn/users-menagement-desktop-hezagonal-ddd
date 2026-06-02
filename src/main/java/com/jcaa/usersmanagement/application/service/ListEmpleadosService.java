package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.ListEmpleadosUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllEmpleadosPort;
import com.jcaa.usersmanagement.domain.model.EmpleadoModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class ListEmpleadosService implements ListEmpleadosUseCase {

  private final GetAllEmpleadosPort getAllEmpleadosPort;

  @Override
  public List<EmpleadoModel> execute() {
    return getAllEmpleadosPort.getAll();
  }
}
