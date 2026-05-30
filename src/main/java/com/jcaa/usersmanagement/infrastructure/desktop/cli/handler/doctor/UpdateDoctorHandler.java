package com.jcaa.usersmanagement.infrastructure.desktop.cli.handler.doctor;

import com.jcaa.usersmanagement.infrastructure.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.io.DoctorResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.desktop.controller.doctor.DoctorController;
import com.jcaa.usersmanagement.infrastructure.desktop.dto.doctor.CreateDoctorRequest;
import com.jcaa.usersmanagement.infrastructure.desktop.dto.doctor.DoctorResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateDoctorHandler implements OperationHandler {

    private final DoctorController doctorController;
    private final ConsoleIO console;
    private final DoctorResponsePrinter printer;

    @Override
    public void handle() {
        // Pedimos el ID solo para identificar el registro a cambiar
        final String id = console.readRequired("ID del Médico a actualizar     : ");

        final CreateDoctorRequest request = new CreateDoctorRequest(
                console.readRequired("Nuevo Nombre                   : "),
                console.readRequired("Nueva Dirección                : "),
                console.readRequired("Nuevo Teléfono                 : "),
                console.readRequired("Nueva Población                : "),
                console.readRequired("Nueva Provincia                : "),
                console.readRequired("Nuevo Código Postal            : "),
                console.readRequired("Nuevo NIF                      : "),
                console.readRequired("Nuevo Num. Seguridad Social    : "),
                console.readRequired("Nueva Licencia Médica          : "),
                console.readRequired("Nueva Categoría                : ")
        );

        try {
            // Llama a tu método original: update(id, request)
            final DoctorResponse updated = doctorController.update(id, request);
            console.println("\n  Médico actualizado correctamente.");
            printer.print(updated);
        } catch (final RuntimeException exception) {
            console.println("  No encontrado: " + exception.getMessage());
        }
    }
}