package com.jcaa.usersmanagement;

import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.ActivityManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.UserManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;

import java.util.Scanner;

public final class Main {

  public static void main(final String[] args) {

    final DependencyContainer container =
            new DependencyContainer();

    try (final Scanner scanner = new Scanner(System.in)) {

      final ConsoleIO console =
              new ConsoleIO(scanner, System.out);

      final UserManagementCli userCli =
              new UserManagementCli(
                      container.userController(),
                      console
              );

      final ActivityManagementCli activityCli =
              new ActivityManagementCli(
                      container.activityController(),
                      console
              );

      boolean running = true;

      while (running) {

        console.println();
        console.println("==========================================");
        console.println("        HOTEL MANAGEMENT SYSTEM");
        console.println("==========================================");
        console.println("[1] Users Management");
        console.println("[2] Activities Management");
        console.println("[0] Exit");
        console.println("==========================================");

        final int option =
                console.readInt("Option: ");

        switch (option) {

          case 1 -> userCli.start();

          case 2 -> activityCli.start();

          case 0 -> {
            console.println("\nGoodbye!");
            running = false;
          }

          default -> console.println("Invalid option.");
        }
      }
    }
  }
}