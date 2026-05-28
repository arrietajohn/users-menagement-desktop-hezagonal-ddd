package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.application.service.nino.dto.NinoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public final class NinoMenuHandler implements OperationHandler {

    private final NinoHandler ninoHandler;
    private final ConsoleIO console;

    @Override
    public void handle() {
        boolean running = true;
        while (running) {
            printNinoMenu();
            final int choice = console.readInt("\n  Option: ");

            try {
                switch (choice) {
                    case 1 -> createNino();
                    case 2 -> listNinos();
                    case 3 -> getNinoById();
                    case 4 -> updateNino();
                    case 5 -> deleteNino();
                    case 0 -> running = false;
                    default -> console.println("  Opción inválida.");
                }
            } catch (final Exception e) {
                console.println("  Error: " + e.getMessage());
            }
        }
    }

    private void printNinoMenu() {
        console.println("\n  === Gestión de Niños ===");
        console.println("  [1] Crear Niño");
        console.println("  [2] Listar Niños");
        console.println("  [3] Buscar Niño por ID");
        console.println("  [4] Actualizar Niño");
        console.println("  [5] Eliminar Niño");
        console.println("  [0] Volver al menú principal");
    }

    private void createNino() {
        final String matricula    = console.readRequired("Matrícula                       : ");
        final String nombre       = console.readRequired("Nombre completo                 : ");
        final String fechaNacStr  = console.readRequired("Fecha de nacimiento (YYYY-MM-DD): ");
        final String fechaIngStr  = console.readRequired("Fecha de ingreso (YYYY-MM-DD)   : ");

        LocalDate fechaNac = LocalDate.parse(fechaNacStr);
        LocalDate fechaIng = LocalDate.parse(fechaIngStr);

        NinoResponse response = ninoHandler.createNino(matricula, nombre, fechaNac, fechaIng);
        console.println("\n  Niño creado exitosamente con ID: " + response.getId());
    }

    private void listNinos() {
        List<NinoResponse> ninos = ninoHandler.listNinos();
        console.println("\nLista de Niños:");
        for (NinoResponse nino : ninos) {
            console.println(" - " + nino.getNombreCompleto() + " (" + nino.getMatricula() + ")");
        }
    }

    private void getNinoById() {
        final Long id = Long.valueOf(console.readRequired("ID del niño: "));
        NinoResponse nino = ninoHandler.getNinoById(id);
        console.println("\nNiño encontrado: " + nino.getNombreCompleto());
    }

    private void updateNino() {
        final Long id = Long.valueOf(console.readRequired("ID del niño: "));
        final String nombre = console.readRequired("Nuevo nombre: ");
        final String fechaNacStr = console.readRequired("Nueva fecha de nacimiento (YYYY-MM-DD): ");
        LocalDate fechaNac = LocalDate.parse(fechaNacStr);
        ninoHandler.updateNino(id, nombre, fechaNac);
        console.println("\nNiño actualizado correctamente.");
    }

    private void deleteNino() {
        final Long id = Long.valueOf(console.readRequired("ID del niño a eliminar: "));
        ninoHandler.deleteNino(id);
        console.println("\nNiño eliminado correctamente.");
    }
}