package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ProyectoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ProyectoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateProyectoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProyectoResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateProyectoHandler implements OperationHandler {

  private final ProyectoController proyectoController;
  private final ConsoleIO console;
  private final ProyectoResponsePrinter printer;

  @Override
  public void handle() {
    final String nombreClave  = console.readRequired("  Nombre clave (máx 50)                          : ");
    final String denominacion = console.readRequired("  Denominación comercial (máx 150)               : ");
    final String fechaInicio  = console.readRequired("  Fecha inicio (YYYY-MM-DD)                      : ");
    final String fechaFin     = console.readOptional( "  Fecha fin (YYYY-MM-DD, Enter para omitir)      : ");
    final String estado       = console.readRequired("  Estado (PLANIFICADO/EN_CURSO/PAUSADO/COMPLETADO/CANCELADO): ");

    final CreateProyectoRequest request = new CreateProyectoRequest(
        nombreClave, denominacion, fechaInicio,
        fechaFin.isBlank() ? null : fechaFin,
        estado, null);

    final ProyectoResponse created = proyectoController.createProyecto(request);
    console.println("\n  Proyecto creado exitosamente.");
    printer.print(created);
  }
}
