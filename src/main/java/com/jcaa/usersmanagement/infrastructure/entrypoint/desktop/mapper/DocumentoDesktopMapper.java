package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateDocumentoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteDocumentoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateDocumentoCommand;
import com.jcaa.usersmanagement.application.service.dto.query.FindDocumentoByIdQuery;
import com.jcaa.usersmanagement.domain.model.DocumentoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateDocumentoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.DocumentoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateDocumentoRequest;

import java.util.List;

public final class DocumentoDesktopMapper {

  private DocumentoDesktopMapper() {}

  public static CreateDocumentoCommand toCreateCommand(final CreateDocumentoRequest request) {
    return new CreateDocumentoCommand(
        request.titulo(), request.tipo(), request.contenido(),
        request.fechaCreacion(), request.estado(), request.autorId());
  }

  public static UpdateDocumentoCommand toUpdateCommand(final UpdateDocumentoRequest request) {
    return new UpdateDocumentoCommand(
        request.id(), request.titulo(), request.tipo(), request.contenido(),
        request.fechaCreacion(), request.estado(), request.autorId());
  }

  public static DeleteDocumentoCommand toDeleteCommand(final Long id) {
    return new DeleteDocumentoCommand(id);
  }

  public static FindDocumentoByIdQuery toFindByIdQuery(final Long id) {
    return new FindDocumentoByIdQuery(id);
  }

  public static DocumentoResponse toResponse(final DocumentoModel documento) {
    return new DocumentoResponse(
        documento.getId().value(),
        documento.getTitulo(),
        documento.getTipo().name(),
        documento.getContenido(),
        documento.getFechaCreacion().toString(),
        documento.getEstado().name(),
        documento.getAutorId().value());
  }

  public static List<DocumentoResponse> toResponseList(final List<DocumentoModel> documentos) {
    return documentos.stream().map(DocumentoDesktopMapper::toResponse).toList();
  }
}
