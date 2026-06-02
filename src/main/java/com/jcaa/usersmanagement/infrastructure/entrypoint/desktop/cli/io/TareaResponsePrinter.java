package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.TareaResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class TareaResponsePrinter {

  private static final String SEPARATOR = "-".repeat(60);
  private static final String ROW_FORMAT = "  %-22s : %s%n";

  private final ConsoleIO console;

  public void print(final TareaResponse response) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID",               String.valueOf(response.id()));
    console.printf(ROW_FORMAT, "Título",           response.titulo());
    console.printf(ROW_FORMAT, "Descripción",      response.descripcion() != null ? response.descripcion() : "-");
    console.printf(ROW_FORMAT, "Prioridad",        response.prioridad());
    console.printf(ROW_FORMAT, "Estado",           response.estado());
    console.printf(ROW_FORMAT, "Fecha vencimiento",response.fechaVencimiento() != null ? response.fechaVencimiento() : "-");
    console.printf(ROW_FORMAT, "Empleado ID",      String.valueOf(response.empleadoId()));
    console.println(SEPARATOR);
  }

  public void printList(final List<TareaResponse> tareas) {
    if (tareas.isEmpty()) {
      console.println("  No se encontraron tareas.");
      return;
    }
    console.printf("%n  Total: %d tarea(s)%n", tareas.size());
    tareas.forEach(this::print);
  }
}
