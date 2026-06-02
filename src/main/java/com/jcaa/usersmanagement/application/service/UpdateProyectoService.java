package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateProyectoUseCase;
import com.jcaa.usersmanagement.application.port.out.GetProyectoByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateProyectoPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateProyectoCommand;
import com.jcaa.usersmanagement.application.service.mapper.ProyectoApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.ProyectoNotFoundException;
import com.jcaa.usersmanagement.domain.model.ProyectoModel;
import com.jcaa.usersmanagement.domain.valueobject.ProyectoId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class UpdateProyectoService implements UpdateProyectoUseCase {

  private final GetProyectoByIdPort getProyectoByIdPort;
  private final UpdateProyectoPort updateProyectoPort;
  private final Validator validator;

  @Override
  public ProyectoModel execute(final UpdateProyectoCommand command) {
    final Set<ConstraintViolation<UpdateProyectoCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }

    final ProyectoId proyectoId = new ProyectoId(command.id());
    getProyectoByIdPort.getById(proyectoId)
        .orElseThrow(() -> ProyectoNotFoundException.becauseIdWasNotFound(command.id()));

    final ProyectoModel proyectoToUpdate = ProyectoApplicationMapper.fromUpdateCommandToModel(command);
    return updateProyectoPort.update(proyectoToUpdate);
  }
}
