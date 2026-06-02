package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.EmpleadoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.EmpleadoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateEmpleadoRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateEmpleadoHandler implements OperationHandler {

  private final EmpleadoController empleadoController;
  private final ConsoleIO console;
  private final EmpleadoResponsePrinter printer;

  @Override
  public void handle() {
    final Long   id                = console.readLong(   "  ID del empleado                     : ");
    final String nombre            = console.readRequired("  Nuevo nombre (máx 100)              : ");
    final String apellido          = console.readRequired("  Nuevo apellido (máx 100)            : ");
    final String email             = console.readRequired("  Nuevo email                         : ");
    final String cargo             = console.readRequired("  Nuevo cargo (máx 100)               : ");
    final String fechaContratacion = console.readRequired("  Fecha contratación (YYYY-MM-DD)     : ");
    final String estado            = console.readRequired("  Estado (ACTIVO/INACTIVO)             : ");

    final UpdateEmpleadoRequest request =
        new UpdateEmpleadoRequest(id, nombre, apellido, email, cargo, fechaContratacion, estado);
    final var updated = empleadoController.updateEmpleado(request);
    console.println("\n  Empleado actualizado exitosamente.");
    printer.print(updated);
  }
}
