package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.RangoMilitarNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.RangoMilitarResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.RangoMilitarController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindRangoMilitarByIdHandler implements OperationHandler {

    private final RangoMilitarController controller;
    private final ConsoleIO console;
    private final RangoMilitarResponsePrinter printer;

    @Override
    public void handle() {
        final String id = console.readRequired("  ID del rango: ");
        try {
            printer.print(controller.findRangoById(id));
        } catch (final RangoMilitarNotFoundException exception) {
            console.println("  Error: " + exception.getMessage());
        }
    }
}
