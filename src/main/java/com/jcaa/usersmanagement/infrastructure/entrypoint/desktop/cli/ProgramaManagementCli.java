package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ProgramaController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProgramaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProgramaResponse;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ProgramaManagementCli {

  private static final String BANNER =
      """
      ==========================================
            Programa Management System
      ==========================================""";

  private static final String MENU_BORDER = "  ==========================================";

  private final ProgramaController programaController;
  private final ConsoleIO console;

  public void start() {
    console.println(BANNER);
    runLoop();
  }

  private void runLoop() {
    boolean running = true;
    while (running) {
      printMenu();
      final int choice = console.readInt("\n  Option: ");

      if (choice == 1) {
        listAllProgramas();
      } else if (choice == 2) {
        findProgramaById();
      } else if (choice == 3) {
        createPrograma();
      } else if (choice == 4) {
        updatePrograma();
      } else if (choice == 5) {
        deletePrograma();
      } else if (choice == 0) {
        console.println("\n  Returning to main menu...");
        running = false;
      } else {
        console.println("  Invalid option. Please try again.");
      }
    }
  }

  private void printMenu() {
    console.println();
    console.println(MENU_BORDER);
    console.println("    Programa Menu");
    console.println(MENU_BORDER);
    console.println("    [1] List all programs");
    console.println("    [2] Find program by ID");
    console.println("    [3] Create program");
    console.println("    [4] Update program");
    console.println("    [5] Delete program");
    console.println("    [0] Back to main menu");
    console.println(MENU_BORDER);
  }

  private void listAllProgramas() {
    try {
      final List<ProgramaResponse> programas = programaController.listAllProgramas();
      if (programas.isEmpty()) {
        console.println("  No programs found.");
        return;
      }
      console.println("\n  --- Programs List ---");
      for (final var prog : programas) {
        console.printf("  ID: %d | Nombre: %s | Genero: %s%n", prog.getId(), prog.getNombre(), prog.getGenero());
      }
    } catch (final Exception exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }

  private void findProgramaById() {
    try {
      final int id = console.readInt("  Enter Program ID: ");
      final ProgramaResponse prog = programaController.findProgramaById((long) id);
      console.println("\n  --- Program Details ---");
      console.printf("  ID: %d%n  Nombre: %s%n  Genero: %s%n", prog.getId(), prog.getNombre(), prog.getGenero());
    } catch (final Exception exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }

  private void createPrograma() {
    try {
      final int id = console.readInt("  Enter Program ID (numbers only): ");
      final String nombre = console.readRequired("  Enter Program Name: ");
      final String genero = console.readRequired("  Enter Program Genre: ");

      final var request = ProgramaRequest.builder()
          .id((long) id)
          .nombre(nombre)
          .genero(genero)
          .build();

      final ProgramaResponse response = programaController.createPrograma(request);
      console.println("\n  Program created successfully!");
      console.printf("  ID: %d | Nombre: %s | Genero: %s%n", response.getId(), response.getNombre(), response.getGenero());
    } catch (final ConstraintViolationException exception) {
      console.println("  Validation errors:");
      exception.getConstraintViolations()
          .forEach(violation -> console.println("    - " + violation.getMessage()));
    } catch (final Exception exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }

  private void updatePrograma() {
    try {
      final int id = console.readInt("  Enter Program ID to Update: ");
      final String nombre = console.readRequired("  Enter New Program Name: ");
      final String genero = console.readRequired("  Enter New Program Genre: ");

      final var request = ProgramaRequest.builder()
          .id((long) id)
          .nombre(nombre)
          .genero(genero)
          .build();

      final ProgramaResponse response = programaController.updatePrograma(request);
      console.println("\n  Program updated successfully!");
      console.printf("  ID: %d | Nombre: %s | Genero: %s%n", response.getId(), response.getNombre(), response.getGenero());
    } catch (final ConstraintViolationException exception) {
      console.println("  Validation errors:");
      exception.getConstraintViolations()
          .forEach(violation -> console.println("    - " + violation.getMessage()));
    } catch (final Exception exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }

  private void deletePrograma() {
    try {
      final int id = console.readInt("  Enter Program ID to Delete: ");
      programaController.deletePrograma((long) id);
      console.println("\n  Program deleted successfully!");
    } catch (final Exception exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }
}
