package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.model.CandidatoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.CandidatoController;

import java.util.List;

public class ListCandidatosHandler implements OperationHandler {

    private final CandidatoController controller;
    private final ConsoleIO io;

    public ListCandidatosHandler(CandidatoController controller, ConsoleIO io) {
        this.controller = controller;
        this.io = io;
    }

    @Override
    public void handle() {
        io.println("=== Lista de Candidatos ===");
        List<CandidatoModel> candidatos = controller.getAll();
        if (candidatos.isEmpty()) {
            io.println("No hay candidatos registrados.");
            return;
        }
        for (CandidatoModel c : candidatos) {
            io.println("-----------------------------");
            io.println("ID: " + c.getId());
            io.println("Nombre: " + c.getNombre());
            io.println("Teléfono: " + c.getTelefono());
            io.println("Tipo: " + c.getTipo());
        }
    }
}