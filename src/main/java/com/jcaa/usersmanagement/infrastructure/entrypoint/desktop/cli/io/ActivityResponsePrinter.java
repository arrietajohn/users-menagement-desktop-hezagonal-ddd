package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ActivityResponse;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ActivityResponsePrinter {

    private static final String SEPARATOR = "-".repeat(60);
    private static final String ROW_FORMAT = "  %-15s : %s%n";

    private final ConsoleIO console;

    public void print(final ActivityResponse response) {

        console.println(SEPARATOR);

        console.printf(ROW_FORMAT, "ID", response.id());
        console.printf(ROW_FORMAT, "Name", response.name());
        console.printf(ROW_FORMAT, "Description", response.description());
        console.printf(ROW_FORMAT, "Day", response.dayOfWeek());
        console.printf(ROW_FORMAT, "Schedule", response.schedule());
        console.printf(ROW_FORMAT, "Price", response.price());
        console.printf(ROW_FORMAT, "Free", response.isFree());
        console.printf(ROW_FORMAT, "Employee ID", response.employeeId());
        console.printf(ROW_FORMAT, "Hotel ID", response.hotelId());

        console.println(SEPARATOR);
    }

    public void printList(final List<ActivityResponse> activities) {

        if (activities.isEmpty()) {
            console.println("  No activities found.");
            return;
        }

        console.printf("%n  Total: %d activity(s)%n", activities.size());

        activities.forEach(this::print);
    }
}
