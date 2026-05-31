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
    final DependencyContainer container = new DependencyContainer(); //Se aplica el principio de Dependency Injection manual - se crean todas la implementaciones concretas (adaptadores)
    try (final Scanner scanner = new Scanner(System.in)) {           //Y se inyectan hacía antentro a trabés de las interfaces (puertos)
      new UserManagementCli(container.userController(), container.sucursalController(), new ConsoleIO(scanner, System.out)).start();
    }
  }
}