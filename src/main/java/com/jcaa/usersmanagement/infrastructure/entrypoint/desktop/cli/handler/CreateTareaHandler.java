package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.TareaResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.TareaController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateTareaRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateTareaHandler implements OperationHandler {

  private final TareaController tareaController;
  private final ConsoleIO console;
  private final TareaResponsePrinter printer;

  @Override
  public void handle() {
    final String titulo          = console.readRequired("  Título (máx 200)                    : ");
    final String descripcion     = console.readOptional( "  Descripción (Enter para omitir)     : ");
    final String prioridad       = console.readRequired("  Prioridad (ALTA/MEDIA/BAJA)         : ");
    final String estado          = console.readRequired("  Estado (PENDIENTE/EN_PROGRESO/...)  : ");
    final String fechaVencimiento= console.readOptional( "  Fecha vencimiento (YYYY-MM-DD)      : ");
    final Long   empleadoId      = console.readLong(    "  ID del empleado asignado            : ");

    final CreateTareaRequest request = new CreateTareaRequest(
        titulo,
        descripcion.isBlank() ? null : descripcion,
        prioridad,
        estado,
        fechaVencimiento.isBlank() ? null : fechaVencimiento,
        empleadoId);
    final var created = tareaController.createTarea(request);
    console.println("\n  Tarea creada exitosamente.");
    printer.print(created);
  }
}
