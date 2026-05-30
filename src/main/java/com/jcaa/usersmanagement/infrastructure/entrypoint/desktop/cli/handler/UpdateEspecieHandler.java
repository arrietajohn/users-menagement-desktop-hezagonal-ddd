package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.EspecieController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.EspecieResponseDto;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateEspecieHandler implements OperationHandler {

    private final EspecieController especieController;
    private final ConsoleIO consoleIO;

    public void handle() {
    consoleIO.println("\n--- Actualizar especie ---");
    String id = consoleIO.readRequired("ID de la especie a actualizar: ");
    String nombreEspañol = consoleIO.readRequired("Nuevo nombre en español: ");
    String descripcion = consoleIO.readOptional("Nueva descripción: ");

    EspecieResponseDto response = especieController.update(id, nombreEspañol, descripcion);
    consoleIO.println("\n✓ Especie actualizada:");
    consoleIO.println("  ID:             " + response.id());
    consoleIO.println("  Nombre español: " + response.nombreEspañol());
    consoleIO.println("  Descripción:    " + response.descripcion());
}
}