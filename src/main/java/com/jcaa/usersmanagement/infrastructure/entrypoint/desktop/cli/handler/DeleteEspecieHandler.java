package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.EspecieController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteEspecieHandler implements OperationHandler {

    private final EspecieController especieController;
    private final ConsoleIO consoleIO;

    public void handle() {
    consoleIO.println("\n--- Eliminar especie ---");
    String id = consoleIO.readRequired("ID de la especie a eliminar: ");
    especieController.delete(id);
    consoleIO.println("\n✓ Especie eliminada correctamente.");
}
}
