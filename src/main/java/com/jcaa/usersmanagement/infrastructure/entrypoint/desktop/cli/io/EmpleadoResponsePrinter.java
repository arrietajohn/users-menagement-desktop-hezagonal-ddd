package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.EmpleadoResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class EmpleadoResponsePrinter {

  private static final String SEPARATOR = "-".repeat(60);
  private static final String ROW_FORMAT = "  %-22s : %s%n";

  private final ConsoleIO console;

  public void print(final EmpleadoResponse response) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID",                 String.valueOf(response.id()));
    console.printf(ROW_FORMAT, "Nombre",             response.nombre());
    console.printf(ROW_FORMAT, "Apellido",           response.apellido());
    console.printf(ROW_FORMAT, "Email",              response.email());
    console.printf(ROW_FORMAT, "Cargo",              response.cargo());
    console.printf(ROW_FORMAT, "Fecha contratación", response.fechaContratacion());
    console.printf(ROW_FORMAT, "Estado",             response.estado());
    console.println(SEPARATOR);
  }

  public void printList(final List<EmpleadoResponse> empleados) {
    if (empleados.isEmpty()) {
      console.println("  No se encontraron empleados.");
      return;
    }
    console.printf("%n  Total: %d empleado(s)%n", empleados.size());
    empleados.forEach(this::print);
  }
}
