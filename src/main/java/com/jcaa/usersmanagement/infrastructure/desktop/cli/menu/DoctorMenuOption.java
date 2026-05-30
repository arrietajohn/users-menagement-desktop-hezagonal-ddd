package com.jcaa.usersmanagement.infrastructure.desktop.cli.menu;

import java.util.Optional;

public enum DoctorMenuOption {
    LIST_DOCTORS(1, "Listar Todos los Médicos"),
    FIND_DOCTOR(2, "Buscar Médico por ID"),
    CREATE_DOCTOR(3, "Registrar un Nuevo Médico"),
    UPDATE_DOCTOR(4, "Actualizar Datos de un Médico"),
    DELETE_DOCTOR(5, "Eliminar un Médico"),
    EXIT(6, "Volver al Menú Principal");

    private final int number;
    private final String description;

    DoctorMenuOption(final int number, final String description) {
        this.number = number;
        this.description = description;
    }

    public int getNumber() { return number; }
    public String getDescription() { return description; }

    public static Optional<DoctorMenuOption> fromNumber(final int number) {
        for (final DoctorMenuOption option : values()) {
            if (option.getNumber() == number) {
                return Optional.of(option);
            }
        }
        return Optional.empty();
    }
}