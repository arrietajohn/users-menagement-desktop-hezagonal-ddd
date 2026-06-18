package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateSessionHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.FindSessionByIdHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListSessionHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu.MenuOption;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.SessionController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.SessionResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UserResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public final class SessionResponsePrinter {

    private static final String SEPARATOR = "-".repeat(52);
    private static final String ROW_FORMAT = "  %-10s : %s%n";

    private final ConsoleIO console;

    public void print(final SessionResponse response) {
        console.println(SEPARATOR);
        console.printf(ROW_FORMAT, "Session ID", response.id());
        console.printf(ROW_FORMAT, "Sala Id", response.salaId());
        console.printf(ROW_FORMAT, "Investigacion Id", response.investigacionId());
        console.printf(ROW_FORMAT, "Ponente ID", response.ponenteId());
        console.printf(ROW_FORMAT, "Chairman Id", response.ChairmanId());
        console.printf(ROW_FORMAT, "Fecha", response.fecha());
        console.printf(ROW_FORMAT, "Hora Inicio", response.horaInicio());
        console.printf(ROW_FORMAT, "Hora Fin", response.horaFin());
        console.println(SEPARATOR);
    }

    public void printList(final List<SessionResponse> sessiones) {
        if (sessiones.isEmpty()) {
            console.println("  No Session found.");
            return;
        }
        console.printf("%n  Total: %d session(s)%n", sessiones.size());
        sessiones.forEach(this::print);
    }
}
