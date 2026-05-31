package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateProyectoUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveProyectoPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateProyectoCommand;
import com.jcaa.usersmanagement.application.service.mapper.ProyectoApplicationMapper;
import com.jcaa.usersmanagement.domain.model.ProyectoModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class CreateProyectoService implements CreateProyectoUseCase {

  private final SaveProyectoPort saveProyectoPort;
  private final Validator validator;

  @Override
  public ProyectoModel execute(final CreateProyectoCommand command) {
    final Set<ConstraintViolation<CreateProyectoCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }

    final ProyectoModel proyectoToSave = ProyectoApplicationMapper.fromCreateCommandToModel(command);
    return saveProyectoPort.save(proyectoToSave);
  }
}
