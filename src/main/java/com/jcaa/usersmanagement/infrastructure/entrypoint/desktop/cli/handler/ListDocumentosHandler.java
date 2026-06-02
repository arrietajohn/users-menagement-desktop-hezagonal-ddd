package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.DocumentoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.DocumentoController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListDocumentosHandler implements OperationHandler {

  private final DocumentoController documentoController;
  private final DocumentoResponsePrinter printer;

  @Override
  public void handle() {
    printer.printList(documentoController.listAllDocumentos());
  }
}
