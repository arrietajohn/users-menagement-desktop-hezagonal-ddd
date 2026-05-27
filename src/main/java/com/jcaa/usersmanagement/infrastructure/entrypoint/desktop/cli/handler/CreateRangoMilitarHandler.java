package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.RangoMilitarAlreadyExistsException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.RangoMilitarResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.RangoMilitarController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateRangoMilitarRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateRangoMilitarHandler implements OperationHandler {

    private final RangoMilitarController controller;
    private final ConsoleIO console;
    private final RangoMilitarResponsePrinter printer;

    @Override
    public void handle() {
        final String id          = console.readRequired("  ID                              : ");
        final String codigo      = console.readRequired("  Codigo (ej: TEN, CAP, SGT)     : ");
        final String nombre      = console.readRequired("  Nombre                          : ");
        final String descripcion = console.readOptional ("  Descripcion (opcional)          : ");
        final String linea       = console.readRequired("  Linea (OFICIAL/SUBOFICIAL/RECLUTA): ");
        final int    meses       = console.readInt     ("  Tiempo minimo ascenso (meses)   : ");

        try {
            final var response = controller.createRango(
                    new CreateRangoMilitarRequest(id, codigo, nombre, descripcion, linea, meses));
            console.println("\n  Rango creado exitosamente.");
            printer.print(response);
        } catch (final RangoMilitarAlreadyExistsException exception) {
            console.println("  Error: " + exception.getMessage());
        }
    }
}
