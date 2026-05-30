package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ResidenciaResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class ResidenciaResponsePrinter {

    private static final String SEPARATOR = "-".repeat(52);
    private static final String ROW_FORMAT = "  %-16s : %s%n";

    private final ConsoleIO console;

    public void print(final ResidenciaResponse response) {
        console.println(SEPARATOR);
        console.printf(ROW_FORMAT, "ID",              String.valueOf(response.id()));
        console.printf(ROW_FORMAT, "Persona ID",      String.valueOf(response.personaId()));
        console.printf(ROW_FORMAT, "Municipio ID",    response.municipioId());
        console.printf(ROW_FORMAT, "Pais extranjero", response.paisExtranjero() != null ? response.paisExtranjero() : "-");
        console.printf(ROW_FORMAT, "Direccion",       response.direccion() != null ? response.direccion() : "-");
        console.printf(ROW_FORMAT, "Fecha inicio",    response.fechaInicio() != null ? response.fechaInicio() : "-");
        console.println(SEPARATOR);
    }

    public void printList(final List<ResidenciaResponse> residencias) {
        if (residencias.isEmpty()) {
            console.println("  No se encontraron residencias.");
            return;
        }
        console.printf("%n  Total: %d residencia(s)%n", residencias.size());
        residencias.forEach(this::print);
    }
}