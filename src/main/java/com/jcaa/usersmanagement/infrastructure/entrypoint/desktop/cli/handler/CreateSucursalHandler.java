package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.SucursalAlreadyExistsException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.SucursalResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.SucursalController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateSucursalRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.SucursalResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateSucursalHandler implements OperationHandler {

    private final SucursalController sucursalController;
    private final ConsoleIO console;
    private final SucursalResponsePrinter printer;

    @Override
    public void handle() {
        final String id          = console.readRequired("ID             : ");
        final String numero      = console.readRequired("Numero         : ");
        final String direccion   = console.readRequired("Direccion      : ");
        final String codigoPostal= console.readRequired("Codigo Postal  : ");
        final String ciudad      = console.readRequired("Ciudad         : ");
        final String bancoId     = console.readRequired("Banco ID       : ");

        try {
            final SucursalResponse created = sucursalController.createSucursal(
                    new CreateSucursalRequest(id, numero, direccion, codigoPostal, ciudad, bancoId));
            console.println("\n  Sucursal creada exitosamente.");
            printer.print(created);
        } catch (final SucursalAlreadyExistsException exception) {
            console.println("  Error: " + exception.getMessage());
        }
    }
}