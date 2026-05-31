package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.model.RepresentanteModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.RepresentanteController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.RepresentanteResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.RepresentanteDesktopMapper;

import java.util.List;

public class ListRepresentantesHandler implements OperationHandler {

    private final RepresentanteController controller;
    private final ConsoleIO io;
    private final RepresentanteDesktopMapper mapper;

    public ListRepresentantesHandler(RepresentanteController controller, ConsoleIO io) {
        this.controller = controller;
        this.io = io;
        this.mapper = new RepresentanteDesktopMapper();
    }

    @Override
    public void handle() {
        try {
            io.println("=== Lista de Representantes ===");
            List<RepresentanteModel> representantes = controller.getAll();
            if (representantes.isEmpty()) {
                io.println("No hay representantes registrados.");
                return;
            }
            for (RepresentanteModel r : representantes) {
                RepresentanteResponse response = mapper.toResponse(r);
                io.println("-----------------------------");
                io.println("ID: " + response.id());
                io.println("Nombre: " + response.nombre());
                io.println("Teléfono: " + response.telefono());
                io.println("Dirección: " + response.direccion());
            }
        } catch (Exception e) {
            io.println("Error inesperado: " + e.getMessage());
        }
    }
}