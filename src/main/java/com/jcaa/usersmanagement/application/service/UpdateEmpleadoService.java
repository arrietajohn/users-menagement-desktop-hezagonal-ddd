package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateEmpleadoUseCase;
import com.jcaa.usersmanagement.application.port.out.GetEmpleadoByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateEmpleadoPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateEmpleadoCommand;
import com.jcaa.usersmanagement.application.service.mapper.EmpleadoApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.EmpleadoNotFoundException;
import com.jcaa.usersmanagement.domain.model.EmpleadoModel;
import com.jcaa.usersmanagement.domain.valueobject.EmpleadoId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class UpdateEmpleadoService implements UpdateEmpleadoUseCase {

  private final GetEmpleadoByIdPort getEmpleadoByIdPort;
  private final UpdateEmpleadoPort updateEmpleadoPort;
  private final Validator validator;

  @Override
  public EmpleadoModel execute(final UpdateEmpleadoCommand command) {
    final Set<ConstraintViolation<UpdateEmpleadoCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
    getEmpleadoByIdPort.getById(new EmpleadoId(command.id()))
        .orElseThrow(() -> EmpleadoNotFoundException.becauseIdWasNotFound(command.id()));
    final EmpleadoModel empleadoToUpdate = EmpleadoApplicationMapper.fromUpdateCommandToModel(command);
    return updateEmpleadoPort.update(empleadoToUpdate);
  }
}
