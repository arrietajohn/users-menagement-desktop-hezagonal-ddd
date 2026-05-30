package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.domain.exception.ResidenciaNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ResidenciaResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ResidenciaController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateResidenciaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ResidenciaResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateResidenciaRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class ResidenciaCli {

    private static final String MENU_BORDER = "  ==========================================";

    private final ResidenciaController residenciaController;
    private final ConsoleIO console;

    public void start() {
        final ResidenciaResponsePrinter printer = new ResidenciaResponsePrinter(console);
        boolean running = true;
        while (running) {
            printMenu();
            final int choice = console.readInt("\n  Opcion: ");
            try {
                switch (choice) {
                    case 1 -> listAll(printer);
                    case 2 -> findById(printer);
                    case 3 -> create(printer);
                    case 4 -> update(printer);
                    case 5 -> delete();
                    case 0 -> running = false;
                    default -> console.println("  Opcion invalida.");
                }
            } catch (final ConstraintViolationException ex) {
                console.println("  Errores de validacion:");
                ex.getConstraintViolations()
                        .forEach(v -> console.println("    - " + v.getMessage()));
            } catch (final ResidenciaNotFoundException ex) {
                console.println("  No encontrado: " + ex.getMessage());
            } catch (final RuntimeException ex) {
                console.println("  Error inesperado: " + ex.getMessage());
            }
        }
    }

    private void printMenu() {
        console.println();
        console.println(MENU_BORDER);
        console.println("    Gestion de Residencias");
        console.println(MENU_BORDER);
        console.println("    [1] Listar todas");
        console.println("    [2] Buscar por ID");
        console.println("    [3] Crear residencia");
        console.println("    [4] Actualizar residencia");
        console.println("    [5] Eliminar residencia");
        console.println("    [0] Volver al menu principal");
        console.println(MENU_BORDER);
    }

    private void listAll(final ResidenciaResponsePrinter printer) {
        final List<ResidenciaResponse> list = residenciaController.listAllResidencias();
        printer.printList(list);
    }

    private void findById(final ResidenciaResponsePrinter printer) {
        final Integer id = console.readInt("ID de residencia: ");
        final ResidenciaResponse response = residenciaController.findResidenciaById(id);
        printer.print(response);
    }

    private void create(final ResidenciaResponsePrinter printer) {
        final Integer personaId  = console.readInt("Persona ID               : ");
        final String municipioId = console.readRequired("Municipio ID             : ");
        final String pais        = console.readOptional("Pais extranjero          : ");
        final String direccion   = console.readOptional("Direccion                : ");
        final String fecha       = console.readRequired("Fecha inicio (YYYY-MM-DD): ");
        final ResidenciaResponse created = residenciaController.createResidencia(
                new CreateResidenciaRequest(
                        personaId,
                        municipioId,
                        pais.isBlank() ? null : pais,
                        direccion.isBlank() ? null : direccion,
                        fecha));
        console.println("\n  Residencia creada exitosamente.");
        printer.print(created);
    }

    private void update(final ResidenciaResponsePrinter printer) {
        final Integer id         = console.readInt("ID de residencia         : ");
        final Integer personaId  = console.readInt("Persona ID               : ");
        final String municipioId = console.readRequired("Municipio ID             : ");
        final String pais        = console.readOptional("Pais extranjero          : ");
        final String direccion   = console.readOptional("Direccion                : ");
        final String fecha       = console.readRequired("Fecha inicio (YYYY-MM-DD): ");
        final ResidenciaResponse updated = residenciaController.updateResidencia(
                new UpdateResidenciaRequest(
                        id,
                        personaId,
                        municipioId,
                        pais.isBlank() ? null : pais,
                        direccion.isBlank() ? null : direccion,
                        fecha));
        console.println("\n  Residencia actualizada exitosamente.");
        printer.print(updated);
    }

    private void delete() {
        final Integer id = console.readInt("ID de residencia a eliminar: ");
        residenciaController.deleteResidencia(id);
        console.println("  Residencia eliminada exitosamente.");
    }
}