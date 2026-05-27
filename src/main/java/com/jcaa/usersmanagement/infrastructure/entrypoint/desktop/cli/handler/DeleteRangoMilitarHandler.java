package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.RangoMilitarNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.RangoMilitarController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteRangoMilitarHandler implements OperationHandler {

    private final RangoMilitarController controller;
    private final ConsoleIO console;

    @Override
    public void handle() {
        final String id = console.readRequired("  ID del rango a eliminar: ");
        try {
            controller.deleteRango(id);
            console.println("\n  Rango eliminado exitosamente.");
        } catch (final RangoMilitarNotFoundException exception) {
            console.println("  Error: " + exception.getMessage());
        }
    }
}
