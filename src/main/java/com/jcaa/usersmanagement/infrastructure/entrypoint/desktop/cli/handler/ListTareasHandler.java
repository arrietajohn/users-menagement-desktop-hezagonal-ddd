package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.TareaResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.TareaController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListTareasHandler implements OperationHandler {

  private final TareaController tareaController;
  private final TareaResponsePrinter printer;

  @Override
  public void handle() {
    printer.printList(tareaController.listAllTareas());
  }
}
