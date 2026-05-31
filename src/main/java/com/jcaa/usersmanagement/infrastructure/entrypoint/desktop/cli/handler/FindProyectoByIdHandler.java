package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.ProyectoNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ProyectoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ProyectoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProyectoResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindProyectoByIdHandler implements OperationHandler {

  private final ProyectoController proyectoController;
  private final ConsoleIO console;
  private final ProyectoResponsePrinter printer;

  @Override
  public void handle() {
    final long id = console.readInt("  ID del proyecto: ");
    try {
      final ProyectoResponse response = proyectoController.findProyectoById(id);
      printer.print(response);
    } catch (final ProyectoNotFoundException exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }
}
