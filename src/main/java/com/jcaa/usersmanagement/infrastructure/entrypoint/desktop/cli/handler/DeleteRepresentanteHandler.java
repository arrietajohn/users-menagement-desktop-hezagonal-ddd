package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.RepresentanteNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.RepresentanteController;

public class DeleteRepresentanteHandler implements OperationHandler {

    private final RepresentanteController controller;
    private final ConsoleIO io;

    public DeleteRepresentanteHandler(RepresentanteController controller, ConsoleIO io) {
        this.controller = controller;
        this.io = io;
    }

    @Override
    public void handle() {
        try {
            io.println("=== Eliminar Representante ===");
            Integer id = io.readInt("ID del representante a eliminar: ");
            controller.delete(id);
            io.println("Representante eliminado exitosamente.");
        } catch (RepresentanteNotFoundException e) {
            io.println("Error: " + e.getMessage());
        } catch (Exception e) {
            io.println("Error inesperado: " + e.getMessage());
        }
    }
}