package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateCandidatoCommand;
import com.jcaa.usersmanagement.domain.enums.CandidatoTipo;
import com.jcaa.usersmanagement.domain.model.CandidatoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.CandidatoController;

public class UpdateCandidatoHandler implements OperationHandler {

    private final CandidatoController controller;
    private final ConsoleIO io;

    public UpdateCandidatoHandler(CandidatoController controller, ConsoleIO io) {
        this.controller = controller;
        this.io = io;
    }

    @Override
    public void handle() {
        io.println("=== Actualizar Candidato ===");
        Integer id = io.readInt("ID del candidato a actualizar: ");
        String nombre = io.readRequired("Nuevo nombre: ");
        String direccion = io.readRequired("Nueva dirección: ");
        String telefono = io.readRequired("Nuevo teléfono: ");
        String fotografia = io.readOptional("Nueva fotografía (URL): ");
        String tipoStr = io.readRequired("Nuevo tipo (ADULTO/NINO): ");
        CandidatoTipo tipo = CandidatoTipo.valueOf(tipoStr.toUpperCase());
        String nombreTutor = io.readOptional("Nuevo tutor (vacío si no aplica): ");

        UpdateCandidatoCommand command = new UpdateCandidatoCommand(
                id, nombre, direccion, telefono, fotografia,
                tipo, nombreTutor.isEmpty() ? null : nombreTutor
        );
        CandidatoModel candidato = controller.update(command);
        io.println("Candidato actualizado: " + candidato.getNombre());
    }
}