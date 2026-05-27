package com.jcaa.usersmanagement;

import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.RangoMilitarCli;
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

      console.println("\n  Seleccione el modulo:");
      console.println("  [1] Gestion de Usuarios");
      console.println("  [2] Gestion de Rangos Militares");
      final int modulo = console.readInt("\n  Opcion: ");

      if (modulo == 2) {
        new RangoMilitarCli(container.rangoMilitarController(), console).start();
      } else {
        new UserManagementCli(container.userController(), console).start();
      }
    }
  }
}
