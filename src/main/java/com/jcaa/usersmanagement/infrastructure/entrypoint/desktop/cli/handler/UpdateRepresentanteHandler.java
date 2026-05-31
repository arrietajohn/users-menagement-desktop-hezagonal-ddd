package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.RepresentanteNotFoundException;
import com.jcaa.usersmanagement.domain.model.RepresentanteModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.RepresentanteController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.RepresentanteResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateRepresentanteRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.RepresentanteDesktopMapper;

public class UpdateRepresentanteHandler implements OperationHandler {

    private final RepresentanteController controller;
    private final ConsoleIO io;
    private final RepresentanteDesktopMapper mapper;

    public UpdateRepresentanteHandler(RepresentanteController controller, ConsoleIO io) {
        this.controller = controller;
        this.io = io;
        this.mapper = new RepresentanteDesktopMapper();
    }

    @Override
    public void handle() {
        try {
            io.println("=== Actualizar Representante ===");
            Integer id = io.readInt("ID del representante a actualizar: ");
            String nombre = io.readRequired("Nuevo nombre: ");
            String telefono = io.readRequired("Nuevo teléfono: ");
            String direccion = io.readRequired("Nueva dirección: ");

            UpdateRepresentanteRequest request = new UpdateRepresentanteRequest(id, nombre, telefono, direccion);
            RepresentanteModel representante = controller.update(mapper.toCommand(request));
            RepresentanteResponse response = mapper.toResponse(representante);
            io.println("Representante actualizado: " + response.nombre());
        } catch (RepresentanteNotFoundException e) {
            io.println("Error: " + e.getMessage());
        } catch (Exception e) {
            io.println("Error inesperado: " + e.getMessage());
        }
    }
}