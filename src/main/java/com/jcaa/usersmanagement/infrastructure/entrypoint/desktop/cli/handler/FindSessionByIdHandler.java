package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.InvalidSessionIdException;
import com.jcaa.usersmanagement.domain.exception.UserNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.SessionResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.SessionController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.SessionResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UserResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindSessionByIdHandler implements OperationHandler {

    private final SessionController sessionController;
    private final ConsoleIO console;
    private final SessionResponsePrinter printer;

    @Override
    public void handle() {
        final String id = console.readRequired("Session ID: ");
        try {
            final SessionResponse session = sessionController.findSessionById(id);
            printer.print(session);
        } catch (final InvalidSessionIdException exception) {
            console.println("  Not found: " + exception.getMessage());
        }
    }
}