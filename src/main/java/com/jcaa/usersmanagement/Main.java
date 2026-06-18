package com.jcaa.usersmanagement;

import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.SessionManagementCli;
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
      final ConsoleIO console = new ConsoleIO(scanner, System.out);
      boolean running = true;
      while (running) {
        console.println("==========================================");
        console.println("  Sistema de Gestión");
        console.println("  [1] Módulo Usuarios");
        console.println("  [2] Módulo Sesiones");
        console.println("  [0] Salir");
        console.println("==========================================");
        final int choice = console.readInt("\n  Opción: ");
        switch (choice) {
          case 1 -> new UserManagementCli(container.userController(), console).start();
          case 2 -> new SessionManagementCli(container.sessionController(), console).start();
          case 0 -> running = false;
        }
      }
    }
  }
}