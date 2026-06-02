package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.FindEmpleadoByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetEmpleadoByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.FindEmpleadoByIdQuery;
import com.jcaa.usersmanagement.domain.exception.EmpleadoNotFoundException;
import com.jcaa.usersmanagement.domain.model.EmpleadoModel;
import com.jcaa.usersmanagement.domain.valueobject.EmpleadoId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindEmpleadoByIdService implements FindEmpleadoByIdUseCase {

  private final GetEmpleadoByIdPort getEmpleadoByIdPort;

  @Override
  public EmpleadoModel execute(final FindEmpleadoByIdQuery query) {
    return getEmpleadoByIdPort.getById(new EmpleadoId(query.id()))
        .orElseThrow(() -> EmpleadoNotFoundException.becauseIdWasNotFound(query.id()));
  }
}
