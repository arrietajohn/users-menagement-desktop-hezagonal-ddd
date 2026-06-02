package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.TareaResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.TareaController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateTareaRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateTareaHandler implements OperationHandler {

  private final TareaController tareaController;
  private final ConsoleIO console;
  private final TareaResponsePrinter printer;

  @Override
  public void handle() {
    final Long   id              = console.readLong(    "  ID de la tarea                      : ");
    final String titulo          = console.readRequired("  Nuevo título (máx 200)              : ");
    final String descripcion     = console.readOptional( "  Nueva descripción (Enter para omitir): ");
    final String prioridad       = console.readRequired("  Prioridad (ALTA/MEDIA/BAJA)         : ");
    final String estado          = console.readRequired("  Estado (PENDIENTE/EN_PROGRESO/...)  : ");
    final String fechaVencimiento= console.readOptional( "  Fecha vencimiento (YYYY-MM-DD)      : ");
    final Long   empleadoId      = console.readLong(    "  ID del empleado asignado            : ");

    final UpdateTareaRequest request = new UpdateTareaRequest(
        id, titulo,
        descripcion.isBlank() ? null : descripcion,
        prioridad, estado,
        fechaVencimiento.isBlank() ? null : fechaVencimiento,
        empleadoId);
    final var updated = tareaController.updateTarea(request);
    console.println("\n  Tarea actualizada exitosamente.");
    printer.print(updated);
  }
}
