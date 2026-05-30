package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.EspecieController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.EspecieResponseDto;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateEspecieHandler implements OperationHandler {

    private final EspecieController especieController;
    private final ConsoleIO consoleIO;

    public void handle() {
    consoleIO.println("\n--- Registrar nueva especie ---");
    String nombreCientifico = consoleIO.readRequired("Nombre científico (ej. Panthera leo): ");
    String nombreEspañol = consoleIO.readRequired("Nombre en español: ");
    String descripcion = consoleIO.readOptional("Descripción (opcional): ");

    EspecieResponseDto response = especieController.create(
        nombreCientifico, nombreEspañol, descripcion);
    consoleIO.println("\n✓ Especie registrada:");
    consoleIO.println("  ID:                " + response.id());
    consoleIO.println("  Nombre científico: " + response.nombreCientifico());
    consoleIO.println("  Nombre español:    " + response.nombreEspañol());
    consoleIO.println("  Descripción:       " + response.descripcion());
}

    private void printEspecie(EspecieResponseDto dto) {
        consoleIO.println("  ID:                " + dto.id());
        consoleIO.println("  Nombre científico: " + dto.nombreCientifico());
        consoleIO.println("  Nombre español:    " + dto.nombreEspañol());
        consoleIO.println("  Descripción:       " + dto.descripcion());
    }
}