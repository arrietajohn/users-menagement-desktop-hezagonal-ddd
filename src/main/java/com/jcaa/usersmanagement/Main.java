package com.jcaa.usersmanagement;

import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.UserManagementCli;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.DoctorManagementCli;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.io.ConsoleIO;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(final String[] args) {
    log.info("Starting Hospital Management System...");
    final DependencyContainer container = new DependencyContainer();

    try (final Scanner scanner = new Scanner(System.in)) {
      final ConsoleIO console = new ConsoleIO(scanner, System.out);
      boolean salir = false;

      while (!salir) {
        console.println();
        console.println("  ==========================================");
        console.println("        SISTEMA HOSPITALARIO CENTRAL");
        console.println("  ==========================================");
        console.println("    [1] Módulo de Usuarios (Sistema Base)");
        console.println("    [2] Módulo de Médicos (Gestión Hospital)");
        console.println("    [3] Salir de la Aplicación");
        console.println("  ==========================================");

        final int opcion = console.readInt("\n  Seleccione una opción: ");

        switch (opcion) {
          case 1 -> {
            console.println("\n  Cargando entorno de Usuarios...\n");
            // Ejecuta el CLI original del profesor
            new UserManagementCli(container.userController(), console).start();
          }
          case 2 -> {
            console.println("\n  Cargando entorno de Médicos...\n");
            // Ejecuta tu nuevo CLI polimórfico de médicos
            new DoctorManagementCli(container.doctorController(), console).start();
          }
          case 3 -> {
            console.println("\n  ¡Adios!");
            salir = true;
          }
          default -> console.println("  Opción inválida. Intente nuevamente.");
        }
      }
    }
  }
}