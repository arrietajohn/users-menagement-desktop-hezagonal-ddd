package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteDocumentoUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteDocumentoPort;
import com.jcaa.usersmanagement.application.port.out.GetDocumentoByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteDocumentoCommand;
import com.jcaa.usersmanagement.domain.exception.DocumentoNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.DocumentoId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class DeleteDocumentoService implements DeleteDocumentoUseCase {

  private final GetDocumentoByIdPort getDocumentoByIdPort;
  private final DeleteDocumentoPort deleteDocumentoPort;
  private final Validator validator;

  @Override
  public void execute(final DeleteDocumentoCommand command) {
    final Set<ConstraintViolation<DeleteDocumentoCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
    getDocumentoByIdPort.getById(new DocumentoId(command.id()))
        .orElseThrow(() -> DocumentoNotFoundException.becauseIdWasNotFound(command.id()));
    deleteDocumentoPort.delete(new DocumentoId(command.id()));
  }
}
