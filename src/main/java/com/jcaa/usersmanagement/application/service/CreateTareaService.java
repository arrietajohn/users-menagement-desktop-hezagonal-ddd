package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateTareaUseCase;
import com.jcaa.usersmanagement.application.port.out.GetEmpleadoByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveTareaPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateTareaCommand;
import com.jcaa.usersmanagement.application.service.mapper.TareaApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.EmpleadoNotFoundException;
import com.jcaa.usersmanagement.domain.model.TareaModel;
import com.jcaa.usersmanagement.domain.valueobject.EmpleadoId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class CreateTareaService implements CreateTareaUseCase {

  private final SaveTareaPort saveTareaPort;
  private final GetEmpleadoByIdPort getEmpleadoByIdPort;
  private final Validator validator;

  @Override
  public TareaModel execute(final CreateTareaCommand command) {
    final Set<ConstraintViolation<CreateTareaCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
    getEmpleadoByIdPort.getById(new EmpleadoId(command.empleadoId()))
        .orElseThrow(() -> EmpleadoNotFoundException.becauseIdWasNotFound(command.empleadoId()));
    final TareaModel tareaToSave = TareaApplicationMapper.fromCreateCommandToModel(command);
    return saveTareaPort.save(tareaToSave);
  }
}
