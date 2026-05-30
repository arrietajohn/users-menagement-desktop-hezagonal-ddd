package com.jcaa.usersmanagement.infrastructure.desktop.cli.handler.doctor;

import com.jcaa.usersmanagement.infrastructure.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.io.DoctorResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.desktop.controller.doctor.DoctorController;
import com.jcaa.usersmanagement.infrastructure.desktop.dto.doctor.CreateDoctorRequest;
import com.jcaa.usersmanagement.infrastructure.desktop.dto.doctor.DoctorResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateDoctorHandler implements OperationHandler {

    private final DoctorController doctorController;
    private final ConsoleIO console;
    private final DoctorResponsePrinter printer;

    @Override
    public void handle() {
        console.println("\n    --- Registrar Nuevo Médico ---");

        // Captura los datos exactos del record original, usando el estilo del profesor
        final CreateDoctorRequest request = new CreateDoctorRequest(
                console.readRequired("Nombre Completo                : "),
                console.readRequired("Dirección                      : "),
                console.readRequired("Teléfono                       : "),
                console.readRequired("Población                      : "),
                console.readRequired("Provincia                      : "),
                console.readRequired("Código Postal                  : "),
                console.readRequired("NIF                            : "),
                console.readRequired("Num. Seguridad Social          : "),
                console.readRequired("Licencia Médica                : "),
                console.readRequired("Categoría (TITULAR / INTERINO) : ")
        );

        try {
            // Sigue el flujo exacto que ya tenías: el request va directo al controlador
            final DoctorResponse created = doctorController.create(request);
            console.println("\n  Médico registrado exitosamente.");
            printer.print(created);
        } catch (final RuntimeException exception) {
            console.println("  Error: " + exception.getMessage());
        }
    }
}