package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetProgramaByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetProgramaByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetProgramaByIdQuery;
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
public final class GetProgramaByIdService implements GetProgramaByIdUseCase {

  private final GetProgramaByIdPort getProgramaByIdPort;
  private final Validator validator;

  @Override
  public ProgramaModel execute(final GetProgramaByIdQuery query) {
    validateQuery(query);
    final ProgramaId id = ProgramaApplicationMapper.fromGetProgramaByIdQueryToProgramaId(query);

    return getProgramaByIdPort
        .getById(id)
        .orElseThrow(() -> ProgramaNotFoundException.becauseIdWasNotFound(id.value().toString()));
  }

  private void validateQuery(final GetProgramaByIdQuery query) {
    final Set<ConstraintViolation<GetProgramaByIdQuery>> violations = validator.validate(query);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
