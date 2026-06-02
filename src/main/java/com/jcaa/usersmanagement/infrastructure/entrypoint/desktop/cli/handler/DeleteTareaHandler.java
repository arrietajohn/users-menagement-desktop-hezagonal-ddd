package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.TareaController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteTareaHandler implements OperationHandler {

  private final TareaController tareaController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final Long id = console.readLong("  ID de la tarea a eliminar: ");
    tareaController.deleteTarea(id);
    console.println("  Tarea eliminada exitosamente.");
  }
}
