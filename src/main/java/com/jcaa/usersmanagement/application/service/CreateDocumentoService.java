package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateDocumentoUseCase;
import com.jcaa.usersmanagement.application.port.out.GetEmpleadoByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveDocumentoPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateDocumentoCommand;
import com.jcaa.usersmanagement.application.service.mapper.DocumentoApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.EmpleadoNotFoundException;
import com.jcaa.usersmanagement.domain.model.DocumentoModel;
import com.jcaa.usersmanagement.domain.valueobject.EmpleadoId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class CreateDocumentoService implements CreateDocumentoUseCase {

  private final SaveDocumentoPort saveDocumentoPort;
  private final GetEmpleadoByIdPort getEmpleadoByIdPort;
  private final Validator validator;

  @Override
  public DocumentoModel execute(final CreateDocumentoCommand command) {
    final Set<ConstraintViolation<CreateDocumentoCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
    getEmpleadoByIdPort.getById(new EmpleadoId(command.autorId()))
        .orElseThrow(() -> EmpleadoNotFoundException.becauseIdWasNotFound(command.autorId()));
    final DocumentoModel documentoToSave = DocumentoApplicationMapper.fromCreateCommandToModel(command);
    return saveDocumentoPort.save(documentoToSave);
  }
}
