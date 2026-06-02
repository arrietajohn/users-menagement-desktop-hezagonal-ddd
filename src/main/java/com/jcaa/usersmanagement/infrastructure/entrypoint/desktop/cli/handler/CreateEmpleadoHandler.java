package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.EmpleadoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.EmpleadoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateEmpleadoRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateEmpleadoHandler implements OperationHandler {

  private final EmpleadoController empleadoController;
  private final ConsoleIO console;
  private final EmpleadoResponsePrinter printer;

  @Override
  public void handle() {
    final String nombre            = console.readRequired("  Nombre (máx 100)                    : ");
    final String apellido          = console.readRequired("  Apellido (máx 100)                  : ");
    final String email             = console.readRequired("  Email                               : ");
    final String cargo             = console.readRequired("  Cargo (máx 100)                     : ");
    final String fechaContratacion = console.readRequired("  Fecha contratación (YYYY-MM-DD)     : ");
    final String estado            = console.readRequired("  Estado (ACTIVO/INACTIVO)             : ");

    final CreateEmpleadoRequest request =
        new CreateEmpleadoRequest(nombre, apellido, email, cargo, fechaContratacion, estado);
    final var created = empleadoController.createEmpleado(request);
    console.println("\n  Empleado creado exitosamente.");
    printer.print(created);
  }
}
