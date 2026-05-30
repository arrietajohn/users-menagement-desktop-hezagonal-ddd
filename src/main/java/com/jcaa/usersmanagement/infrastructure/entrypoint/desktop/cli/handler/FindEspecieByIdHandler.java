package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.EspecieController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.EspecieResponseDto;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindEspecieByIdHandler implements OperationHandler {

    private final EspecieController especieController;
    private final ConsoleIO consoleIO;

    public void handle() {
    consoleIO.println("\n--- Buscar especie por ID ---");
    String id = consoleIO.readRequired("ID de la especie: ");
    EspecieResponseDto response = especieController.getById(id);
    consoleIO.println("\nEspecie encontrada:");
    consoleIO.println("  ID:                " + response.id());
    consoleIO.println("  Nombre científico: " + response.nombreCientifico());
    consoleIO.println("  Nombre español:    " + response.nombreEspañol());
    consoleIO.println("  Descripción:       " + response.descripcion());
}
}
