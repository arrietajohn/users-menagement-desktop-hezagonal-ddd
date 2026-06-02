package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateDocumentoUseCase;
import com.jcaa.usersmanagement.application.port.out.GetDocumentoByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateDocumentoPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateDocumentoCommand;
import com.jcaa.usersmanagement.application.service.mapper.DocumentoApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.DocumentoNotFoundException;
import com.jcaa.usersmanagement.domain.model.DocumentoModel;
import com.jcaa.usersmanagement.domain.valueobject.DocumentoId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class UpdateDocumentoService implements UpdateDocumentoUseCase {

  private final GetDocumentoByIdPort getDocumentoByIdPort;
  private final UpdateDocumentoPort updateDocumentoPort;
  private final Validator validator;

  @Override
  public DocumentoModel execute(final UpdateDocumentoCommand command) {
    final Set<ConstraintViolation<UpdateDocumentoCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
    getDocumentoByIdPort.getById(new DocumentoId(command.id()))
        .orElseThrow(() -> DocumentoNotFoundException.becauseIdWasNotFound(command.id()));
    final DocumentoModel documentoToUpdate = DocumentoApplicationMapper.fromUpdateCommandToModel(command);
    return updateDocumentoPort.update(documentoToUpdate);
  }
}
