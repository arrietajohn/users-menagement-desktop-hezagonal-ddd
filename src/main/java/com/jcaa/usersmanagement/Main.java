package com.jcaa.usersmanagement;

import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.UserManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.ProgramaManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(final String[] args) {
    log.info("Starting General Management System...");
    final DependencyContainer container = new DependencyContainer();
    try (final Scanner scanner = new Scanner(System.in)) {
      final ConsoleIO console = new ConsoleIO(scanner, System.out);
      boolean running = true;
      while (running) {
        console.println();
        console.println("==========================================");
        console.println("       SISTEMA DE GESTION ACADEMICA");
        console.println("==========================================");
        console.println("  [1] Modulo de Usuarios (Original)");
        console.println("  [2] Modulo de Programas (Hexagonal CRUDL)");
        console.println("  [0] Salir");
        console.println("==========================================");
        final int choice = console.readInt("\n  Seleccione una opcion: ");
        if (choice == 1) {
          new UserManagementCli(container.userController(), console).start();
        } else if (choice == 2) {
          new ProgramaManagementCli(container.programaController(), console).start();
        } else if (choice == 0) {
          console.println("\n  ¡Adios!\n");
          running = false;
        } else {
          console.println("  Opcion invalida. Intente de nuevo.");
        }
      }
    }
  }
}