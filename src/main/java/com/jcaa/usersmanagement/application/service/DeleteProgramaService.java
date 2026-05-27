package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteProgramaUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteProgramaPort;
import com.jcaa.usersmanagement.application.port.out.GetProgramaByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteProgramaCommand;
import com.jcaa.usersmanagement.application.service.mapper.ProgramaApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.ProgramaNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.ProgramaId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteProgramaService implements DeleteProgramaUseCase {

  private final DeleteProgramaPort deleteProgramaPort;
  private final GetProgramaByIdPort getProgramaByIdPort;
  private final Validator validator;

  @Override
  public void execute(final DeleteProgramaCommand command) {
    validateCommand(command);
    final ProgramaId id = ProgramaApplicationMapper.fromDeleteCommandToProgramaId(command);
    ensureProgramaExists(id);
    deleteProgramaPort.deleteById(id);
  }

  private void validateCommand(final DeleteProgramaCommand command) {
    final Set<ConstraintViolation<DeleteProgramaCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureProgramaExists(final ProgramaId id) {
    getProgramaByIdPort
        .getById(id)
        .orElseThrow(() -> ProgramaNotFoundException.becauseIdWasNotFound(id.value().toString()));
  }
}
