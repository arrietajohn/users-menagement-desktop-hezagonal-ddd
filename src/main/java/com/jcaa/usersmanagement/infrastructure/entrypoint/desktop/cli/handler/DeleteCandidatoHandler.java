package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.CandidatoController;

public class DeleteCandidatoHandler implements OperationHandler {

    private final CandidatoController controller;
    private final ConsoleIO io;

    public DeleteCandidatoHandler(CandidatoController controller, ConsoleIO io) {
        this.controller = controller;
        this.io = io;
    }

    @Override
    public void handle() {
        io.println("=== Eliminar Candidato ===");
        Integer id = io.readInt("ID del candidato a eliminar: ");
        controller.delete(id);
        io.println("Candidato eliminado exitosamente.");
    }
}