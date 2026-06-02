package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.ListDocumentosUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllDocumentosPort;
import com.jcaa.usersmanagement.domain.model.DocumentoModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class ListDocumentosService implements ListDocumentosUseCase {

  private final GetAllDocumentosPort getAllDocumentosPort;

  @Override
  public List<DocumentoModel> execute() {
    return getAllDocumentosPort.getAll();
  }
}
