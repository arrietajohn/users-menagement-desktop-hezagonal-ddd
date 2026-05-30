package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.model.CandidatoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.CandidatoController;

public class FindCandidatoByIdHandler implements OperationHandler {

    private final CandidatoController controller;
    private final ConsoleIO io;

    public FindCandidatoByIdHandler(CandidatoController controller, ConsoleIO io) {
        this.controller = controller;
        this.io = io;
    }

    @Override
    public void handle() {
        io.println("=== Buscar Candidato por ID ===");
        Integer id = io.readInt("ID: ");
        CandidatoModel c = controller.getById(id);
        io.println("-----------------------------");
        io.println("ID: " + c.getId());
        io.println("Nombre: " + c.getNombre());
        io.println("Dirección: " + c.getDireccion());
        io.println("Teléfono: " + c.getTelefono());
        io.println("Fecha nacimiento: " + c.getFechaNacimiento());
        io.println("Tipo: " + c.getTipo());
        io.println("Tutor: " + c.getNombreTutor());
    }
}