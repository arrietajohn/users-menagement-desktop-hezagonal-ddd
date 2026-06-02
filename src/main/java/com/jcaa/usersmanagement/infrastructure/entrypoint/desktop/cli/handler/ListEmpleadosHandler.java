package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.EmpleadoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.EmpleadoController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListEmpleadosHandler implements OperationHandler {

  private final EmpleadoController empleadoController;
  private final EmpleadoResponsePrinter printer;

  @Override
  public void handle() {
    printer.printList(empleadoController.listAllEmpleados());
  }
}
