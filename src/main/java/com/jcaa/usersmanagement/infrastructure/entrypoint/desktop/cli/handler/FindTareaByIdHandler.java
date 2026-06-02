package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.TareaResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.TareaController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindTareaByIdHandler implements OperationHandler {

  private final TareaController tareaController;
  private final ConsoleIO console;
  private final TareaResponsePrinter printer;

  @Override
  public void handle() {
    final Long id = console.readLong("  ID de la tarea: ");
    printer.print(tareaController.findTareaById(id));
  }
}
