package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.DocumentoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.DocumentoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateDocumentoRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateDocumentoHandler implements OperationHandler {

  private final DocumentoController documentoController;
  private final ConsoleIO console;
  private final DocumentoResponsePrinter printer;

  @Override
  public void handle() {
    final String titulo       = console.readRequired("  Título (máx 200)                         : ");
    final String tipo         = console.readRequired("  Tipo (CONTRATO/INFORME/FACTURA/OTRO)      : ");
    final String contenido    = console.readOptional( "  Contenido (Enter para omitir)             : ");
    final String fechaCreacion= console.readRequired("  Fecha creación (YYYY-MM-DD)               : ");
    final String estado       = console.readRequired("  Estado (BORRADOR/PUBLICADO/ARCHIVADO)      : ");
    final Long   autorId      = console.readLong(    "  ID del autor (empleado)                   : ");

    final CreateDocumentoRequest request = new CreateDocumentoRequest(
        titulo, tipo,
        contenido.isBlank() ? null : contenido,
        fechaCreacion, estado, autorId);
    final var created = documentoController.createDocumento(request);
    console.println("\n  Documento creado exitosamente.");
    printer.print(created);
  }
}
