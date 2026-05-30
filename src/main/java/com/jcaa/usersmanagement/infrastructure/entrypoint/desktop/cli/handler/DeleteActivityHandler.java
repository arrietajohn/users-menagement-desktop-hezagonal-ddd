package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ActivityController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteActivityHandler implements OperationHandler {

    private final ActivityController activityController;
    private final ConsoleIO console;

    @Override
    public void handle() {

        final String id =
                console.readRequired("Activity ID : ");

        activityController.deleteActivity(id);

        console.println("\n  Activity deleted successfully.");
    }
}
