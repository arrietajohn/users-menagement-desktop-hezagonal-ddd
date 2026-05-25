package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ActivityResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ActivityController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindActivityByIdHandler implements OperationHandler {

    private final ActivityController activityController;
    private final ConsoleIO console;
    private final ActivityResponsePrinter printer;

    @Override
    public void handle() {

        final String id =
                console.readRequired("Activity ID : ");

        final var activity =
                activityController.findActivityById(id);

        if (activity.isPresent()) {

            printer.print(activity.get());

        } else {

            console.println("  Activity not found.");
        }
    }
}
