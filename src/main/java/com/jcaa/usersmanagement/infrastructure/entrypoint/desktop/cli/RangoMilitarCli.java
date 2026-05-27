package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateRangoMilitarHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DeleteRangoMilitarHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.FindRangoMilitarByIdHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListRangosMilitaresHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.UpdateRangoMilitarHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.RangoMilitarResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu.RangoMenuOption;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.RangoMilitarController;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public final class RangoMilitarCli {

    private static final String BANNER =
            """
            ==========================================
                 Rangos Militares - Gestion CRUDL
            ==========================================""";

    private static final String MENU_BORDER =
            "  ==========================================";

    private final RangoMilitarController controller;
    private final ConsoleIO console;

    public void start() {
        console.println(BANNER);
        final RangoMilitarResponsePrinter printer = new RangoMilitarResponsePrinter(console);
        runLoop(buildHandlers(printer));
    }

    private void runLoop(final Map<RangoMenuOption, OperationHandler> handlers) {
        boolean running = true;
        while (running) {
            printMenu();
            final int choice = console.readInt("\n  Opcion: ");
            final Optional<RangoMenuOption> option = RangoMenuOption.fromNumber(choice);

            if (option.isEmpty()) {
                console.println("  Opcion invalida. Intente de nuevo.");
            } else if (option.get() == RangoMenuOption.EXIT) {
                console.println("\n  Hasta luego!\n");
                running = false;
            } else {
                executeHandler(handlers, option.get());
            }
        }
    }

    private void executeHandler(
            final Map<RangoMenuOption, OperationHandler> handlers,
            final RangoMenuOption option) {
        try {
            handlers.get(option).handle();
        } catch (final ConstraintViolationException exception) {
            console.println("  Errores de validacion:");
            exception.getConstraintViolations()
                    .forEach(v -> console.println("    - " + v.getMessage()));
        } catch (final RuntimeException exception) {
            console.println("  Error inesperado: " + exception.getMessage());
        }
    }

    private Map<RangoMenuOption, OperationHandler> buildHandlers(
            final RangoMilitarResponsePrinter printer) {
        return Map.of(
                RangoMenuOption.LIST_RANGOS,   new ListRangosMilitaresHandler(controller, printer),
                RangoMenuOption.FIND_RANGO,    new FindRangoMilitarByIdHandler(controller, console, printer),
                RangoMenuOption.CREATE_RANGO,  new CreateRangoMilitarHandler(controller, console, printer),
                RangoMenuOption.UPDATE_RANGO,  new UpdateRangoMilitarHandler(controller, console, printer),
                RangoMenuOption.DELETE_RANGO,  new DeleteRangoMilitarHandler(controller, console));
    }

    private void printMenu() {
        console.println();
        console.println(MENU_BORDER);
        console.println("    Menu Rangos Militares");
        console.println(MENU_BORDER);
        for (final RangoMenuOption option : RangoMenuOption.values()) {
            console.printf("    [%d] %s%n", option.getNumber(), option.getDescription());
        }
        console.println(MENU_BORDER);
    }
}
