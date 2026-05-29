package com.jcaa.usersmanagement;

import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.UserManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;

// 1. CORRECCIÓN DE IMPORTS:
// Si estos imports se ponen rojos, bórralos por completo, haz clic en la palabra roja de abajo en el código,
// presiona Alt + Enter y selecciona "Import class" para que IntelliJ encuentre la carpeta real.
import com.jcaa.usersmanagement.infrastructure.entrypoint.console.RoomManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.ActivityManagementCli;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    DependencyContainer container = new DependencyContainer();
    Scanner scanner = new Scanner(System.in);

    boolean running = true;
    while (running) {
      System.out.println("\n=======================================");
      System.out.println("          SISTEMA PRINCIPAL            ");
      System.out.println("=======================================");
      System.out.println("1. Gestionar Usuarios");
      System.out.println("2. Gestionar Actividades");
      System.out.println("3. Gestionar Habitaciones");
      System.out.println("4. Salir del Sistema");
      System.out.print("Seleccione un módulo: ");

      String option = scanner.nextLine();

      switch (option) {
        case "1" -> {
          System.out.println("\nAbriendo gestión de usuarios...");
          ConsoleIO console = new ConsoleIO(scanner, System.out);
          UserManagementCli userCli = new UserManagementCli(container.userController(), console);
          userCli.start();
        }

        case "2" -> {
          System.out.println("\nAbriendo gestión de actividades...");
          ConsoleIO console = new ConsoleIO(scanner, System.out);

          // REVISIÓN DE ACTIVIDADES:
          // Si '(container.activityController(), console)' sale malo, es porque quitamos el controlador del contenedor.
          // Prueba borrando 'container.activityController(), ' y deja solo 'console' o 'scanner' según lo pida el IDE.
          // Si sigue en rojo, hazle caso a IntelliJ presionando Alt + Enter para ver qué constructor te sugiere.
          ActivityManagementCli activityCli = new ActivityManagementCli(container.activityController(), console);
          activityCli.start();
        }

        case "3" -> {
          System.out.println("\nAbriendo gestión de habitaciones...");
          // REVISIÓN DE HABITACIONES:
          // En tu código original que sí funcionaba, llamabas a RoomManagementCli pasándole el 'container.roomController()'.
          // Si da error en 'start()', es porque el método para iniciar en tu clase de la Unidad III no se llama 'start',
          // tal vez se llama 'ejecutar()', 'init()' o 'showMenu()'.
          RoomManagementCli roomCli = new RoomManagementCli(container.roomController());
          roomCli.start();
        }

        case "4" -> {
          System.out.println("Cerrando el sistema. Adiós");
          running = false;
        }
        default -> System.out.println("Opción inválida.");
      }
    }
    scanner.close();
  }
}
