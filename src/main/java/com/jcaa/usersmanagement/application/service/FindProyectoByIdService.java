package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.FindProyectoByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetProyectoByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.FindProyectoByIdQuery;
import com.jcaa.usersmanagement.domain.exception.ProyectoNotFoundException;
import com.jcaa.usersmanagement.domain.model.ProyectoModel;
import com.jcaa.usersmanagement.domain.valueobject.ProyectoId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindProyectoByIdService implements FindProyectoByIdUseCase {

  private final GetProyectoByIdPort getProyectoByIdPort;

  @Override
  public ProyectoModel execute(final FindProyectoByIdQuery query) {
    final ProyectoId proyectoId = new ProyectoId(query.id());
    return getProyectoByIdPort.getById(proyectoId)
        .orElseThrow(() -> ProyectoNotFoundException.becauseIdWasNotFound(query.id()));
  }
}
