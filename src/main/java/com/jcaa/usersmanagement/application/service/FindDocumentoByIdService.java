package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.FindDocumentoByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetDocumentoByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.FindDocumentoByIdQuery;
import com.jcaa.usersmanagement.domain.exception.DocumentoNotFoundException;
import com.jcaa.usersmanagement.domain.model.DocumentoModel;
import com.jcaa.usersmanagement.domain.valueobject.DocumentoId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindDocumentoByIdService implements FindDocumentoByIdUseCase {

  private final GetDocumentoByIdPort getDocumentoByIdPort;

  @Override
  public DocumentoModel execute(final FindDocumentoByIdQuery query) {
    return getDocumentoByIdPort.getById(new DocumentoId(query.id()))
        .orElseThrow(() -> DocumentoNotFoundException.becauseIdWasNotFound(query.id()));
  }
}
