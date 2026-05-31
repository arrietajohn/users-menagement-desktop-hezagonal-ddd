package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.ProyectoNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ProyectoController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteProyectoHandler implements OperationHandler {

  private final ProyectoController proyectoController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final long id = console.readInt("  ID del proyecto a eliminar: ");
    try {
      proyectoController.deleteProyecto(id);
      console.println("  Proyecto eliminado exitosamente.");
    } catch (final ProyectoNotFoundException exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }
}
