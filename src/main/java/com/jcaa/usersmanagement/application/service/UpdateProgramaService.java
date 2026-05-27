package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateProgramaUseCase;
import com.jcaa.usersmanagement.application.port.out.GetProgramaByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveProgramaPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateProgramaCommand;
import com.jcaa.usersmanagement.application.service.mapper.ProgramaApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.ProgramaNotFoundException;
import com.jcaa.usersmanagement.domain.model.ProgramaModel;
import com.jcaa.usersmanagement.domain.valueobject.ProgramaId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateProgramaService implements UpdateProgramaUseCase {

  private final SaveProgramaPort saveProgramaPort;
  private final GetProgramaByIdPort getProgramaByIdPort;
  private final Validator validator;

  @Override
  public ProgramaModel execute(final UpdateProgramaCommand command) {
    validateCommand(command);
    final ProgramaId id = new ProgramaId(command.id());
    ensureProgramaExists(id);

    final ProgramaModel programaToUpdate = ProgramaApplicationMapper.fromUpdateCommandToModel(command);
    return saveProgramaPort.save(programaToUpdate);
  }

  private void validateCommand(final UpdateProgramaCommand command) {
    final Set<ConstraintViolation<UpdateProgramaCommand>> violations = validator.validate(command);
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
