package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.EmpleadoController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteEmpleadoHandler implements OperationHandler {

  private final EmpleadoController empleadoController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final Long id = console.readLong("  ID del empleado a eliminar: ");
    empleadoController.deleteEmpleado(id);
    console.println("  Empleado eliminado exitosamente.");
  }
}
