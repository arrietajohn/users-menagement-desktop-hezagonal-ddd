package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ActivityResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ActivityController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateActivityRequest;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public final class CreateActivityHandler implements OperationHandler {

    private final ActivityController activityController;
    private final ConsoleIO console;
    private final ActivityResponsePrinter printer;

    @Override
    public void handle() {

        final String id = console.readRequired("ID                  : ");
        final String name = console.readRequired("Name                : ");
        final String description = console.readRequired("Description         : ");
        final String dayOfWeek = console.readRequired("Day of week         : ");
        final String schedule = console.readRequired("Schedule            : ");

        final boolean isFree =
                Boolean.parseBoolean(console.readRequired("Is free? (true/false): "));

        BigDecimal price = BigDecimal.ZERO;

        if (!isFree) {
            price = new BigDecimal(
                    console.readRequired("Price               : "));
        }

        final String employeeId =
                console.readRequired("Employee ID         : ");

        final int hotelId =
                Integer.parseInt(console.readRequired("Hotel ID            : "));

        activityController.createActivity(
                new CreateActivityRequest(
                        id,
                        name,
                        description,
                        dayOfWeek,
                        schedule,
                        price,
                        isFree,
                        employeeId,
                        hotelId
                )
        );

        console.println("\n  Activity created successfully.");
    }
}