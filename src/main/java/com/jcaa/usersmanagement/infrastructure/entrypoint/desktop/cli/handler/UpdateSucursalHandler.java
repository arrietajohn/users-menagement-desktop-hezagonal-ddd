package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.SucursalNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.SucursalResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.SucursalController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.SucursalResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateSucursalRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateSucursalHandler implements OperationHandler {

    private final SucursalController sucursalController;
    private final ConsoleIO console;
    private final SucursalResponsePrinter printer;

    @Override
    public void handle() {
        final String id          = console.readRequired("ID             : ");
        final String numero      = console.readRequired("Nuevo numero   : ");
        final String direccion   = console.readRequired("Nueva direccion: ");
        final String codigoPostal= console.readRequired("Codigo Postal  : ");
        final String ciudad      = console.readRequired("Nueva ciudad   : ");
        final String bancoId     = console.readRequired("Banco ID       : ");

        try {
            final SucursalResponse updated = sucursalController.updateSucursal(
                    new UpdateSucursalRequest(id, numero, direccion, codigoPostal, ciudad, bancoId));
            console.println("\n  Sucursal actualizada exitosamente.");
            printer.print(updated);
        } catch (final SucursalNotFoundException exception) {
            console.println("  Not found: " + exception.getMessage());
        }
    }
}