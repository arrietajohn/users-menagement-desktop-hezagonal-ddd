package com.jcaa.usersmanagement.infrastructure.desktop.cli.handler.doctor;

import com.jcaa.usersmanagement.infrastructure.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.desktop.controller.doctor.DoctorController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteDoctorHandler implements OperationHandler {

    private final DoctorController doctorController;
    private final ConsoleIO console;

    @Override
    public void handle() {
        final String id = console.readRequired("ID del Médico a eliminar: ");
        try {
            doctorController.delete(id);
            console.println("  Médico eliminado exitosamente.");
        } catch (final RuntimeException exception) {
            console.println("  Error: " + exception.getMessage());
        }
    }
}