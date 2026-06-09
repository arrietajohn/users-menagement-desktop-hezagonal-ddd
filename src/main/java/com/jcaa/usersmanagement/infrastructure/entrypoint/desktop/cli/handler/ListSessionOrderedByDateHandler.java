package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.application.port.in.GetSessionOrderedByDateUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.SessionResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.SessionController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.SessionResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class ListSessionOrderedByDateHandler implements OperationHandler {

    private final SessionController sessionController;
    private final SessionResponsePrinter printer;

    @Override
    public void handle(){
        try {
            final List<SessionResponse> sessions =sessionController.getSessionOrderedByDate();
            printer.printList(sessions);
        }
            catch (final Exception exception) {
            printer.printList(java.util.List.of());
            }
    }
}

