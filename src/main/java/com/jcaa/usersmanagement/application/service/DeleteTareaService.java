package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteTareaUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteTareaPort;
import com.jcaa.usersmanagement.application.port.out.GetTareaByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteTareaCommand;
import com.jcaa.usersmanagement.domain.exception.TareaNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.TareaId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class DeleteTareaService implements DeleteTareaUseCase {

  private final GetTareaByIdPort getTareaByIdPort;
  private final DeleteTareaPort deleteTareaPort;
  private final Validator validator;

  @Override
  public void execute(final DeleteTareaCommand command) {
    final Set<ConstraintViolation<DeleteTareaCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
    getTareaByIdPort.getById(new TareaId(command.id()))
        .orElseThrow(() -> TareaNotFoundException.becauseIdWasNotFound(command.id()));
    deleteTareaPort.delete(new TareaId(command.id()));
  }
}
