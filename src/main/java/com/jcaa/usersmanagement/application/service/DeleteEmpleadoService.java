package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteEmpleadoUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteEmpleadoPort;
import com.jcaa.usersmanagement.application.port.out.GetEmpleadoByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteEmpleadoCommand;
import com.jcaa.usersmanagement.domain.exception.EmpleadoNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.EmpleadoId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class DeleteEmpleadoService implements DeleteEmpleadoUseCase {

  private final GetEmpleadoByIdPort getEmpleadoByIdPort;
  private final DeleteEmpleadoPort deleteEmpleadoPort;
  private final Validator validator;

  @Override
  public void execute(final DeleteEmpleadoCommand command) {
    final Set<ConstraintViolation<DeleteEmpleadoCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
    getEmpleadoByIdPort.getById(new EmpleadoId(command.id()))
        .orElseThrow(() -> EmpleadoNotFoundException.becauseIdWasNotFound(command.id()));
    deleteEmpleadoPort.delete(new EmpleadoId(command.id()));
  }
}
