package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteProyectoUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteProyectoPort;
import com.jcaa.usersmanagement.application.port.out.GetProyectoByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteProyectoCommand;
import com.jcaa.usersmanagement.domain.exception.ProyectoNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.ProyectoId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteProyectoService implements DeleteProyectoUseCase {

  private final GetProyectoByIdPort getProyectoByIdPort;
  private final DeleteProyectoPort deleteProyectoPort;

  @Override
  public void execute(final DeleteProyectoCommand command) {
    final ProyectoId proyectoId = new ProyectoId(command.id());
    getProyectoByIdPort.getById(proyectoId)
        .orElseThrow(() -> ProyectoNotFoundException.becauseIdWasNotFound(command.id()));
    deleteProyectoPort.delete(proyectoId);
  }
}
