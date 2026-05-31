package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.AerolineaController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteAerolineaHandler implements OperationHandler {

    private final AerolineaController controller;
    private final ConsoleIO console;

    @Override
    public void handle() {

        Integer id = console.readInt("ID a eliminar: ");

        controller.delete(id);

        console.println("✔ Eliminada correctamente");
    }
}