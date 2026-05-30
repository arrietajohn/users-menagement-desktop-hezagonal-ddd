package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.application.service.dto.command.CreateCandidatoCommand;
import com.jcaa.usersmanagement.domain.enums.CandidatoTipo;
import com.jcaa.usersmanagement.domain.model.CandidatoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.CandidatoController;

import java.time.LocalDate;

public class CreateCandidatoHandler implements OperationHandler {

    private final CandidatoController controller;
    private final ConsoleIO io;

    public CreateCandidatoHandler(CandidatoController controller, ConsoleIO io) {
        this.controller = controller;
        this.io = io;
    }

    @Override
    public void handle() {
        io.println("=== Crear Candidato ===");
        Integer id = io.readInt("ID: ");
        String nombre = io.readRequired("Nombre: ");
        String direccion = io.readRequired("Dirección: ");
        String telefono = io.readRequired("Teléfono: ");
        String fechaStr = io.readRequired("Fecha nacimiento (YYYY-MM-DD): ");
        LocalDate fechaNacimiento = LocalDate.parse(fechaStr);
        String fotografia = io.readOptional("Fotografía (URL): ");
        String tipoStr = io.readRequired("Tipo (ADULTO/NINO): ");
        CandidatoTipo tipo = CandidatoTipo.valueOf(tipoStr.toUpperCase());
        String nombreTutor = io.readOptional("Nombre tutor (vacío si no aplica): ");

        CreateCandidatoCommand command = new CreateCandidatoCommand(
                id, nombre, direccion, telefono, fechaNacimiento,
                fotografia, tipo, nombreTutor.isEmpty() ? null : nombreTutor
        );
        CandidatoModel candidato = controller.create(command);
        io.println("Candidato creado exitosamente: " + candidato.getNombre());
    }
}