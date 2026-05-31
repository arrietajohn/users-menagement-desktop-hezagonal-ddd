package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.RepresentanteNotFoundException;
import com.jcaa.usersmanagement.domain.model.RepresentanteModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.RepresentanteController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.RepresentanteResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.RepresentanteDesktopMapper;

public class FindRepresentanteByIdHandler implements OperationHandler {

    private final RepresentanteController controller;
    private final ConsoleIO io;
    private final RepresentanteDesktopMapper mapper;

    public FindRepresentanteByIdHandler(RepresentanteController controller, ConsoleIO io) {
        this.controller = controller;
        this.io = io;
        this.mapper = new RepresentanteDesktopMapper();
    }

    @Override
    public void handle() {
        try {
            io.println("=== Buscar Representante por ID ===");
            Integer id = io.readInt("ID: ");
            RepresentanteModel representante = controller.getById(id);
            RepresentanteResponse response = mapper.toResponse(representante);
            io.println("-----------------------------");
            io.println("ID: " + response.id());
            io.println("Nombre: " + response.nombre());
            io.println("Teléfono: " + response.telefono());
            io.println("Dirección: " + response.direccion());
        } catch (RepresentanteNotFoundException e) {
            io.println("Error: " + e.getMessage());
        } catch (Exception e) {
            io.println("Error inesperado: " + e.getMessage());
        }
    }
}