package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ActivityController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateActivityRequest;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public final class UpdateActivityHandler implements OperationHandler {

    private final ActivityController activityController;
    private final ConsoleIO console;

    @Override
    public void handle() {

        final String id =
                console.readRequired("Activity ID         : ");

        final String name =
                console.readRequired("New name            : ");

        final String description =
                console.readRequired("New description     : ");

        final String dayOfWeek =
                console.readRequired("New day of week     : ");

        final String schedule =
                console.readRequired("New schedule        : ");

        final boolean isFree =
                Boolean.parseBoolean(
                        console.readRequired("Is free? (true/false): ")
                );

        BigDecimal price = BigDecimal.ZERO;

        if (!isFree) {

            price = new BigDecimal(
                    console.readRequired("New price           : ")
            );
        }

        activityController.updateActivity(
                new UpdateActivityRequest(
                        id,
                        name,
                        description,
                        dayOfWeek,
                        schedule,
                        price,
                        isFree
                )
        );

        console.println("\n  Activity updated successfully.");
    }
}