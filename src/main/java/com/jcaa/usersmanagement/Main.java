package com.jcaa.usersmanagement;

import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.UserManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;


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


          ActivityManagementCli activityCli = new ActivityManagementCli(container.activityController(), console);
          activityCli.start();
        }

        case "3" -> {
          System.out.println("\nAbriendo gestión de habitaciones...");

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
