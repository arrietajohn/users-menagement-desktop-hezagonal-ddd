package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.SessionResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.SessionController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.SessionResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class FindSessionByChairmanHandler implements OperationHandler {

    private final SessionController sessionController;
    private final ConsoleIO console;
    private final SessionResponsePrinter printer;

    @Override
    public void handle(){
        final String chairmanId = console.readRequired("Chairman: ");
        try {
            final List<SessionResponse> sessions = sessionController.getSessionByChairman(chairmanId);
            printer.printList(sessions);
        } catch ( final Exception exception) {
            console.println("No se encontraron sesiones: " + exception.getMessage());
        }
    }
}