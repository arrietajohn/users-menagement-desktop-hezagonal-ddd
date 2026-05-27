package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateProgramaUseCase;
import com.jcaa.usersmanagement.application.port.out.GetProgramaByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveProgramaPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateProgramaCommand;
import com.jcaa.usersmanagement.application.service.mapper.ProgramaApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.ProgramaAlreadyExistsException;
import com.jcaa.usersmanagement.domain.model.ProgramaModel;
import com.jcaa.usersmanagement.domain.valueobject.ProgramaId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateProgramaService implements CreateProgramaUseCase {

  private final SaveProgramaPort saveProgramaPort;
  private final GetProgramaByIdPort getProgramaByIdPort;
  private final Validator validator;

  @Override
  public ProgramaModel execute(final CreateProgramaCommand command) {
    validateCommand(command);
    final ProgramaId id = new ProgramaId(command.id());
    ensureIdIsNotTaken(id);

    final ProgramaModel programaToSave = ProgramaApplicationMapper.fromCreateCommandToModel(command);
    return saveProgramaPort.save(programaToSave);
  }

  private void validateCommand(final CreateProgramaCommand command) {
    final Set<ConstraintViolation<CreateProgramaCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureIdIsNotTaken(final ProgramaId id) {
    getProgramaByIdPort
        .getById(id)
        .ifPresent(
            ignored -> {
              throw ProgramaAlreadyExistsException.becauseIdAlreadyExists(id.value().toString());
            });
  }
}
