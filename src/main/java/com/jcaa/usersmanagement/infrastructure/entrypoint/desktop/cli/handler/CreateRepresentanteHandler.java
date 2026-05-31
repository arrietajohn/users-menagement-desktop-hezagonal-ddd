package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.RepresentanteNotFoundException;
import com.jcaa.usersmanagement.domain.model.RepresentanteModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.RepresentanteController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateRepresentanteRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.RepresentanteResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.RepresentanteDesktopMapper;

public class CreateRepresentanteHandler implements OperationHandler {

    private final RepresentanteController controller;
    private final ConsoleIO io;
    private final RepresentanteDesktopMapper mapper;

    public CreateRepresentanteHandler(RepresentanteController controller, ConsoleIO io) {
        this.controller = controller;
        this.io = io;
        this.mapper = new RepresentanteDesktopMapper();
    }

    @Override
    public void handle() {
        try {
            io.println("=== Crear Representante ===");
            Integer id = io.readInt("ID: ");
            String nombre = io.readRequired("Nombre: ");
            String telefono = io.readRequired("Teléfono: ");
            String direccion = io.readRequired("Dirección: ");

            CreateRepresentanteRequest request = new CreateRepresentanteRequest(id, nombre, telefono, direccion);
            RepresentanteModel representante = controller.create(mapper.toCommand(request));
            RepresentanteResponse response = mapper.toResponse(representante);
            io.println("Representante creado exitosamente: " + response.nombre());
        } catch (RepresentanteNotFoundException e) {
            io.println("Error: " + e.getMessage());
        } catch (Exception e) {
            io.println("Error inesperado: " + e.getMessage());
        }
    }
}