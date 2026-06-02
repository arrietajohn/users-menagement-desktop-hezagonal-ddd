package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.EmpleadoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.EmpleadoController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindEmpleadoByIdHandler implements OperationHandler {

  private final EmpleadoController empleadoController;
  private final ConsoleIO console;
  private final EmpleadoResponsePrinter printer;

  @Override
  public void handle() {
    final Long id = console.readLong("  ID del empleado: ");
    printer.print(empleadoController.findEmpleadoById(id));
  }
}
