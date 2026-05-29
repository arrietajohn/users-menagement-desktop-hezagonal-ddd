package com.jcaa.usersmanagement.infrastructure.desktop.cli.handler.doctor;

import com.jcaa.usersmanagement.infrastructure.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.io.DoctorResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.desktop.controller.doctor.DoctorController;
import com.jcaa.usersmanagement.infrastructure.desktop.dto.doctor.DoctorResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindDoctorByIdHandler implements OperationHandler {

    private final DoctorController doctorController;
    private final ConsoleIO console;
    private final DoctorResponsePrinter printer;

    @Override
    public void handle() {
        final String id = console.readRequired("ID del Médico a buscar: ");
        try {
            final DoctorResponse doctor = doctorController.getById(id);
            printer.print(doctor);
        } catch (final RuntimeException exception) {
            console.println("  Error: " + exception.getMessage());
        }
    }
}