package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateDocumentoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateDocumentoCommand;
import com.jcaa.usersmanagement.domain.enums.DocumentoEstado;
import com.jcaa.usersmanagement.domain.enums.DocumentoTipo;
import com.jcaa.usersmanagement.domain.model.DocumentoModel;
import com.jcaa.usersmanagement.domain.valueobject.DocumentoId;
import com.jcaa.usersmanagement.domain.valueobject.EmpleadoId;

import java.time.LocalDate;

public final class DocumentoApplicationMapper {

  private DocumentoApplicationMapper() {}

  public static DocumentoModel fromCreateCommandToModel(final CreateDocumentoCommand command) {
    return DocumentoModel.create(
        command.titulo(),
        DocumentoTipo.fromString(command.tipo()),
        command.contenido(),
        LocalDate.parse(command.fechaCreacion()),
        DocumentoEstado.fromString(command.estado()),
        new EmpleadoId(command.autorId()));
  }

  public static DocumentoModel fromUpdateCommandToModel(final UpdateDocumentoCommand command) {
    return new DocumentoModel(
        new DocumentoId(command.id()),
        command.titulo(),
        DocumentoTipo.fromString(command.tipo()),
        command.contenido(),
        LocalDate.parse(command.fechaCreacion()),
        DocumentoEstado.fromString(command.estado()),
        new EmpleadoId(command.autorId()));
  }
}
