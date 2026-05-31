package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.AerolineaController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListAerolineasHandler implements OperationHandler {

    private final AerolineaController controller;
    private final ConsoleIO console;

    @Override
    public void handle() {

        console.println("\n===== AEROLÍNEAS =====");

        var list = controller.listAll();

        if (list.isEmpty()) {
            console.println("No hay aerolíneas.");
            return;
        }

        for (var a : list) {
            console.println("ID: " + a.idAerolinea());
            console.println("Nombre: " + a.nombre());
            console.println("País: " + a.paisOrigen());
            console.println("-------------------");
        }
    }
}