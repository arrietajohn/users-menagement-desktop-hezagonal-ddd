package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.SucursalNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.SucursalController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteSucursalHandler implements OperationHandler {

    private final SucursalController sucursalController;
    private final ConsoleIO console;

    @Override
    public void handle() {
        final String id = console.readRequired("ID de sucursal a eliminar: ");
        try {
            sucursalController.deleteSucursal(id);
            console.println("  Sucursal eliminada exitosamente.");
        } catch (final SucursalNotFoundException exception) {
            console.println("  Not found: " + exception.getMessage());
        }
    }
}