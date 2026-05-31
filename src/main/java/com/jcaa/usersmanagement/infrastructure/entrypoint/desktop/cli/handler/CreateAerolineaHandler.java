package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.AerolineaController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateAerolineaRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateAerolineaHandler implements OperationHandler {

    private final AerolineaController controller;
    private final ConsoleIO console;

    @Override
    public void handle() {
        final String nombre = console.readRequired("Nombre aerolínea: ");
        final String pais = console.readRequired("País origen: ");

        controller.create(new CreateAerolineaRequest(nombre, pais));

        console.println("✔ Aerolínea creada correctamente");
    }
}