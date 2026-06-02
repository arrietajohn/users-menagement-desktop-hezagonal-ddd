package com.jcaa.usersmanagement;

import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.UserManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import lombok.extern.java.Log;

import java.util.Scanner;

@Log
public final class Main {

  public static void main(final String[] args) {
    log.info("Starting Users Management System...");
    final DependencyContainer container = new DependencyContainer();
    try (final Scanner scanner = new Scanner(System.in)) {
      new UserManagementCli(
              container.userController(),
              container.proyectoController(),
              container.empleadoController(),
              container.tareaController(),
              container.documentoController(),
              new ConsoleIO(scanner, System.out))
          .start();
    }
  }
}
