package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.EspecieController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.EspecieResponseDto;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class ListEspeciesHandler implements OperationHandler {

    private final EspecieController especieController;
    private final ConsoleIO consoleIO;

    public void handle() {
        consoleIO.println("\n--- Lista de especies ---");
        List<EspecieResponseDto> especies = especieController.getAll();
        if (especies.isEmpty()) {
            consoleIO.println("No hay especies registradas.");
            return;
        }
        for (EspecieResponseDto dto : especies) {
            consoleIO.println("  [" + dto.id() + "] "
                + dto.nombreCientifico() + " - " + dto.nombreEspañol());
        }
    }
}
