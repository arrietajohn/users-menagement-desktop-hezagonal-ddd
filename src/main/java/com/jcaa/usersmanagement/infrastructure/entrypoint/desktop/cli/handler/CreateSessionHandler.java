package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.UserAlreadyExistsException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.SessionResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.SessionController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateSessionRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateUserRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.SessionResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UserResponse;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@RequiredArgsConstructor
public final class CreateSessionHandler implements OperationHandler {

    private final SessionController sessionController;
    private final ConsoleIO console;
    private final SessionResponsePrinter printer;

    @Override
    public void handle() {
        final String id = console.readRequired("ID                    : ");
        final String SalaID = console.readRequired("SalaID                : ");
        final String InvestigacionId = console.readRequired("InvestigacionId       : ");
        final String PonenteId = console.readRequired("PonenteId             : ");
        final String ChairmanId = console.readRequired("ChairmanId            : ");
        final String Fecha = console.readRequired("Fecha                 : ");
        final String horaInicio = console.readRequired("HoraInicio            : ");
        final String horaFin = console.readRequired("HoraFin               : ");

        try {
            final SessionResponse created = sessionController.createSession(
                    new CreateSessionRequest(
                            id,
                            SalaID,
                            InvestigacionId,
                            PonenteId,
                            ChairmanId,
                            Fecha,
                            horaInicio,
                            horaFin
                    )
            );

            console.println("\n  Session created successfully.");
            console.println("  ID: " + created.id()); // Cambiado por si no usas un printer especial

        } catch (final Exception exception) {
            console.println("  Error: " + exception.getMessage());
        }
    }
}