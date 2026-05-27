package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu;

import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RangoMenuOption {

    LIST_RANGOS(1, "List all rangos militares"),
    FIND_RANGO(2, "Find rango by ID"),
    CREATE_RANGO(3, "Create rango militar"),
    UPDATE_RANGO(4, "Update rango militar"),
    DELETE_RANGO(5, "Delete rango militar"),
    EXIT(0, "Exit");

    private final int number;
    private final String description;

    public static Optional<RangoMenuOption> fromNumber(final int number) {
        for (final RangoMenuOption option : values()) {
            if (option.number == number) {
                return Optional.of(option);
            }
        }
        return Optional.empty();
    }
}
