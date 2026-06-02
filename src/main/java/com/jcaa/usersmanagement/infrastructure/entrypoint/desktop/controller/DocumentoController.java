package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateDocumentoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteDocumentoUseCase;
import com.jcaa.usersmanagement.application.port.in.FindDocumentoByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.ListDocumentosUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateDocumentoUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateDocumentoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.DocumentoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateDocumentoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.DocumentoDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class DocumentoController {

  private final CreateDocumentoUseCase createDocumentoUseCase;
  private final UpdateDocumentoUseCase updateDocumentoUseCase;
  private final DeleteDocumentoUseCase deleteDocumentoUseCase;
  private final FindDocumentoByIdUseCase findDocumentoByIdUseCase;
  private final ListDocumentosUseCase listDocumentosUseCase;

  public List<DocumentoResponse> listAllDocumentos() {
    return DocumentoDesktopMapper.toResponseList(listDocumentosUseCase.execute());
  }

  public DocumentoResponse findDocumentoById(final Long id) {
    return DocumentoDesktopMapper.toResponse(
        findDocumentoByIdUseCase.execute(DocumentoDesktopMapper.toFindByIdQuery(id)));
  }

  public DocumentoResponse createDocumento(final CreateDocumentoRequest request) {
    return DocumentoDesktopMapper.toResponse(
        createDocumentoUseCase.execute(DocumentoDesktopMapper.toCreateCommand(request)));
  }

  public DocumentoResponse updateDocumento(final UpdateDocumentoRequest request) {
    return DocumentoDesktopMapper.toResponse(
        updateDocumentoUseCase.execute(DocumentoDesktopMapper.toUpdateCommand(request)));
  }

  public void deleteDocumento(final Long id) {
    deleteDocumentoUseCase.execute(DocumentoDesktopMapper.toDeleteCommand(id));
  }
}
