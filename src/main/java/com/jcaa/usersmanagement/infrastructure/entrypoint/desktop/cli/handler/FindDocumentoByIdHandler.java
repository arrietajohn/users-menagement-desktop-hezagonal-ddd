package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.DocumentoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.DocumentoController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindDocumentoByIdHandler implements OperationHandler {

  private final DocumentoController documentoController;
  private final ConsoleIO console;
  private final DocumentoResponsePrinter printer;

  @Override
  public void handle() {
    final Long id = console.readLong("  ID del documento: ");
    printer.print(documentoController.findDocumentoById(id));
  }
}
