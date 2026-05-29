package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateActivityHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DeleteActivityHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.FindActivityByIdHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListActivitiesHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.UpdateActivityHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ActivityResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ActivityController;

import java.util.Map;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ActivityManagementCli {

    private static final String BANNER =
            """
            ==========================================
                 Activities Management System
            ==========================================""";

    private final ActivityController activityController;
    private final ConsoleIO console;

    public void start() {

        console.println(BANNER);

        final ActivityResponsePrinter printer =
                new ActivityResponsePrinter(console);

        runLoop(buildHandlers(printer));
    }

    private void runLoop(final Map<Integer, OperationHandler> handlers) {

        boolean running = true;

        while (running) {

            printMenu();

            final int option =
                    console.readInt("\n  Option: ");

            switch (option) {

                case 1 -> handlers.get(1).handle();

                case 2 -> handlers.get(2).handle();

                case 3 -> handlers.get(3).handle();

                case 4 -> handlers.get(4).handle();

                case 5 -> handlers.get(5).handle();

                case 0 -> {
                    console.println("\n  Returning to main menu...\n");
                    running = false;
                }

                default -> console.println("  Invalid option.");
            }
        }
    }

    private Map<Integer, OperationHandler> buildHandlers(
            final ActivityResponsePrinter printer) {

        return Map.of(
                1, new ListActivitiesHandler(activityController, printer),
                2, new FindActivityByIdHandler(activityController, console, printer),
                3, new CreateActivityHandler(activityController, console, printer),
                4, new UpdateActivityHandler(activityController, console),
                5, new DeleteActivityHandler(activityController, console)
        );
    }

    private void printMenu() {

        console.println();
        console.println("  ==========================================");
        console.println("    Activities Menu");
        console.println("  ==========================================");
        console.println("    [1] List all activities");
        console.println("    [2] Find activity by ID");
        console.println("    [3] Create activity");
        console.println("    [4] Update activity");
        console.println("    [5] Delete activity");
        console.println("    [0] Back");
        console.println("  ==========================================");
    }
}
