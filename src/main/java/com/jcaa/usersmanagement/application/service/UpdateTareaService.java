package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateTareaUseCase;
import com.jcaa.usersmanagement.application.port.out.GetTareaByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateTareaPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateTareaCommand;
import com.jcaa.usersmanagement.application.service.mapper.TareaApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.TareaNotFoundException;
import com.jcaa.usersmanagement.domain.model.TareaModel;
import com.jcaa.usersmanagement.domain.valueobject.TareaId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class UpdateTareaService implements UpdateTareaUseCase {

  private final GetTareaByIdPort getTareaByIdPort;
  private final UpdateTareaPort updateTareaPort;
  private final Validator validator;

  @Override
  public TareaModel execute(final UpdateTareaCommand command) {
    final Set<ConstraintViolation<UpdateTareaCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
    getTareaByIdPort.getById(new TareaId(command.id()))
        .orElseThrow(() -> TareaNotFoundException.becauseIdWasNotFound(command.id()));
    final TareaModel tareaToUpdate = TareaApplicationMapper.fromUpdateCommandToModel(command);
    return updateTareaPort.update(tareaToUpdate);
  }
}
