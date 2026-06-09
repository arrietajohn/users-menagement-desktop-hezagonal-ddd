package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.application.port.in.GetSessionsByDateUseCase;
import com.jcaa.usersmanagement.domain.model.Session;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.SessionPersistenceMapper;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.SessionResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.SessionController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.SessionResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class FindSessionByDateHandler implements OperationHandler {

    private final SessionController sessionController;
    private final ConsoleIO console;
    private final SessionResponsePrinter printer;

    @Override
    public void handle(){
        final String fecha = console.readRequired("Fecha (YYYY-MM-DD): ");
        try {
            final List<SessionResponse> sessions = sessionController.getSessionByDate(fecha);
            printer.printList(sessions);
        } catch ( final Exception exception) {
            console.println("No se encontraron sesiones: " + exception.getMessage());
        }
    }
}