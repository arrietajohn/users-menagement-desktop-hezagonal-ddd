package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateEmpleadoUseCase;
import com.jcaa.usersmanagement.application.port.out.GetEmpleadoByEmailPort;
import com.jcaa.usersmanagement.application.port.out.SaveEmpleadoPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateEmpleadoCommand;
import com.jcaa.usersmanagement.application.service.mapper.EmpleadoApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.EmpleadoAlreadyExistsException;
import com.jcaa.usersmanagement.domain.model.EmpleadoModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class CreateEmpleadoService implements CreateEmpleadoUseCase {

  private final SaveEmpleadoPort saveEmpleadoPort;
  private final GetEmpleadoByEmailPort getEmpleadoByEmailPort;
  private final Validator validator;

  @Override
  public EmpleadoModel execute(final CreateEmpleadoCommand command) {
    final Set<ConstraintViolation<CreateEmpleadoCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
    getEmpleadoByEmailPort.getByEmail(command.email()).ifPresent(e -> {
      throw EmpleadoAlreadyExistsException.becauseEmailAlreadyExists(command.email());
    });
    final EmpleadoModel empleadoToSave = EmpleadoApplicationMapper.fromCreateCommandToModel(command);
    return saveEmpleadoPort.save(empleadoToSave);
  }
}
