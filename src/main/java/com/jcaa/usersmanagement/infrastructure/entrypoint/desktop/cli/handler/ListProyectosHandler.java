package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ProyectoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ProyectoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProyectoResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class ListProyectosHandler implements OperationHandler {

  private final ProyectoController proyectoController;
  private final ProyectoResponsePrinter printer;

  @Override
  public void handle() {
    final List<ProyectoResponse> proyectos = proyectoController.listAllProyectos();
    printer.printList(proyectos);
  }
}
