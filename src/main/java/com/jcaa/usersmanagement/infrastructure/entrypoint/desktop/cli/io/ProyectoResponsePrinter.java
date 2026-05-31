package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProyectoResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class ProyectoResponsePrinter {

  private static final String SEPARATOR = "-".repeat(60);
  private static final String ROW_FORMAT = "  %-18s : %s%n";

  private final ConsoleIO console;

  public void print(final ProyectoResponse response) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID",            String.valueOf(response.id()));
    console.printf(ROW_FORMAT, "Nombre clave",  response.nombreClave());
    console.printf(ROW_FORMAT, "Denominación",  response.denominacion());
    console.printf(ROW_FORMAT, "Fecha inicio",  response.fechaInicio());
    console.printf(ROW_FORMAT, "Fecha fin",     response.fechaFin() != null ? response.fechaFin() : "-");
    console.printf(ROW_FORMAT, "Estado",        response.estado());
    console.printf(ROW_FORMAT, "Promotor ID",   response.promotorId() != null ? String.valueOf(response.promotorId()) : "-");
    console.println(SEPARATOR);
  }

  public void printList(final List<ProyectoResponse> proyectos) {
    if (proyectos.isEmpty()) {
      console.println("  No se encontraron proyectos.");
      return;
    }
    console.printf("%n  Total: %d proyecto(s)%n", proyectos.size());
    proyectos.forEach(this::print);
  }
}
