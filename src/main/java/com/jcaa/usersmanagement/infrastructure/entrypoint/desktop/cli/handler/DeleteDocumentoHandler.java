package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.DocumentoController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteDocumentoHandler implements OperationHandler {

  private final DocumentoController documentoController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final Long id = console.readLong("  ID del documento a eliminar: ");
    documentoController.deleteDocumento(id);
    console.println("  Documento eliminado exitosamente.");
  }
}
