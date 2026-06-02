package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.DocumentoResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class DocumentoResponsePrinter {

  private static final String SEPARATOR = "-".repeat(60);
  private static final String ROW_FORMAT = "  %-22s : %s%n";

  private final ConsoleIO console;

  public void print(final DocumentoResponse response) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID",             String.valueOf(response.id()));
    console.printf(ROW_FORMAT, "Título",         response.titulo());
    console.printf(ROW_FORMAT, "Tipo",           response.tipo());
    console.printf(ROW_FORMAT, "Contenido",      response.contenido() != null ? response.contenido() : "-");
    console.printf(ROW_FORMAT, "Fecha creación", response.fechaCreacion());
    console.printf(ROW_FORMAT, "Estado",         response.estado());
    console.printf(ROW_FORMAT, "Autor ID",       String.valueOf(response.autorId()));
    console.println(SEPARATOR);
  }

  public void printList(final List<DocumentoResponse> documentos) {
    if (documentos.isEmpty()) {
      console.println("  No se encontraron documentos.");
      return;
    }
    console.printf("%n  Total: %d documento(s)%n", documentos.size());
    documentos.forEach(this::print);
  }
}
