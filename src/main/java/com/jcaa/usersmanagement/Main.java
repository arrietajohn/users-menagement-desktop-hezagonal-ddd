package com.jcaa.usersmanagement;

import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.UserManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.ProyectoViewCLI; // <-- TU IMPORT
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
      boolean salir = false;
      while (!salir) {
        System.out.println("\n========================================");
        System.out.println("   SISTEMA DE GESTIÓN EMPRESARIAL CLI   ");
        System.out.println("========================================");
        System.out.println("1. Gestionar Usuarios");
        System.out.println("2. Gestionar Proyectos (Módulo Sebastián)");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");

        String opcion = scanner.nextLine();
        switch (opcion) {
          case "1" -> new UserManagementCli(container.userController(), new ConsoleIO(scanner, System.out)).start();
          case "2" -> new ProyectoViewCLI(container).start(); // <-- CONEXIÓN A TU MÓDULO
          case "3" -> {
            System.out.println("Saliendo del sistema...");
            salir = true;
          }
          default -> System.out.println("Opción no válida. Intente de nuevo.");
        }
      }
    }
  }
}