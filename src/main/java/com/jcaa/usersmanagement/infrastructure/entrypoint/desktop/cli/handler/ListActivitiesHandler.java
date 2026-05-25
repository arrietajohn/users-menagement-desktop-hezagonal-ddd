package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ActivityResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ActivityController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListActivitiesHandler implements OperationHandler {

    private final ActivityController activityController;
    private final ActivityResponsePrinter printer;

    @Override
    public void handle() {

        final var activities =
                activityController.listAllActivities();

        printer.printList(activities);
    }
}
