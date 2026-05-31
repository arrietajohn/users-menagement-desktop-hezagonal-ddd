package com.jcaa.usersmanagement;

import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.UserManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(final String[] args) {
    log.info("Starting Users Management System...");
    final DependencyContainer container = new DependencyContainer();
    try (final Scanner scanner = new Scanner(System.in)) {
      boolean running = true;
      while (running) {
        System.out.println("\n========================================");
        System.out.println("         Sistema de Gestión");
        System.out.println("========================================");
        System.out.println("[1] Gestión de Usuarios");
        System.out.println("[2] Gestión de Clientes");
        System.out.println("[0] Salir");
        System.out.println("========================================");
        System.out.print("Opción: ");
        final String option = scanner.nextLine().trim();

        switch (option) {
          case "1" -> new UserManagementCli(
                  container.userController(),
                  new ConsoleIO(scanner, System.out)).start();
          case "2" -> container.clienteController().menu(scanner);
          case "0" -> running = false;
          default -> System.out.println("Opción no válida.");
        }
      }
    }
  }
}