package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.RangoMilitarNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.RangoMilitarResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.RangoMilitarController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateRangoMilitarRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateRangoMilitarHandler implements OperationHandler {

    private final RangoMilitarController controller;
    private final ConsoleIO console;
    private final RangoMilitarResponsePrinter printer;

    @Override
    public void handle() {
        final String id          = console.readRequired("  ID del rango a actualizar       : ");
        final String codigo      = console.readRequired("  Nuevo codigo (ej: TEN, CAP)     : ");
        final String nombre      = console.readRequired("  Nuevo nombre                    : ");
        final String descripcion = console.readLine    ("  Nueva descripcion (opcional)    : ");
        final String linea       = console.readRequired("  Linea (OFICIAL/SUBOFICIAL/RECLUTA): ");
        final int    meses       = console.readInt     ("  Tiempo minimo ascenso (meses)   : ");

        try {
            final var response = controller.updateRango(
                    new UpdateRangoMilitarRequest(id, codigo, nombre, descripcion, linea, meses));
            console.println("\n  Rango actualizado exitosamente.");
            printer.print(response);
        } catch (final RangoMilitarNotFoundException exception) {
            console.println("  Error: " + exception.getMessage());
        }
    }
}
