package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.*;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.SessionResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu.MenuOption;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.SessionController;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public final class SessionManagementCli {
    private static final String BANNER =
            """
            ==========================================
                  Session Management System
             ==========================================""";

    private static final String MENU_BORDER = "  ==========================================";

    private final SessionController sessionController;
    private final ConsoleIO console;

    public void start() {
        console.println(BANNER);
        final SessionResponsePrinter printer = new SessionResponsePrinter(console);
        runLoop(buildHandlers(printer));
    }

    private void runLoop(final Map<MenuOption, OperationHandler> handlers) {
        boolean running = true;
        while (running) {
            printMenu();
            final int choice = console.readInt("\n  Option: ");
            final Optional<MenuOption> option = MenuOption.fromNumber(choice);

            if (option.isEmpty()) {
                console.println("  Invalid option. Please try again.");
            } else if (option.get() == MenuOption.EXIT) {
                console.println("\n  Regresate al menu anterior\n");
                running = false;
            } else {
                executeHandler(handlers, option.get());
            }
        }
    }

    private void executeHandler(
            final Map<MenuOption, OperationHandler> handlers, final MenuOption option) {
        try {
            handlers.get(option).handle();
        } catch (final ConstraintViolationException exception) {
            console.println("  Validation errors:");
            exception.getConstraintViolations()
                    .forEach(violation -> console.println("    - " + violation.getMessage()));
        } catch (final RuntimeException exception) {
            console.println("  Unexpected error: " + exception.getMessage());
        }
    }

    private Map<MenuOption, OperationHandler> buildHandlers(final SessionResponsePrinter printer) {
        return Map.of(
                MenuOption.LIST_SESSIONS,  new ListSessionHandler(sessionController, printer),
                MenuOption.CREATE_SESSION, new CreateSessionHandler(sessionController, console, printer),
                MenuOption.FIND_SESSION,   new FindSessionByIdHandler(sessionController, console, printer),
                MenuOption.FIND_SESSION_BY_DATE, new FindSessionByDateHandler(sessionController, console, printer));
    }

    private void printMenu() {
        console.println();
        console.println(MENU_BORDER);
        console.println("    Menu Sesiones");
        console.println(MENU_BORDER);
        for (final MenuOption option : MenuOption.values()) {
            if (option == MenuOption.CREATE_SESSION
                    || option == MenuOption.LIST_SESSIONS
                    || option == MenuOption.FIND_SESSION
                    || option == MenuOption.EXIT
                    || option == MenuOption.FIND_SESSION_BY_DATE) {
                console.printf("    [%d] %s%n", option.getNumber(), option.getDescription());
            }
        }
        console.println(MENU_BORDER);
    }
}


