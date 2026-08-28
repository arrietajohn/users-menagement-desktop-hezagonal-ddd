package com.jcaa.usersmanagement;

import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.UserManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.ofertaempleo.cli.OfertaEmpleoCli;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {

    log.info("Starting Users Management System...");

    DependencyContainer container = new DependencyContainer();

    Scanner scanner = new Scanner(System.in);
    ConsoleIO io = new ConsoleIO(scanner, System.out);

    UserManagementCli userCli =
            new UserManagementCli(container.userController(), io);

    OfertaEmpleoCli ofertaCli =
            new OfertaEmpleoCli(container.ofertaEmpleoController(), scanner);

    boolean running = true;

    while (running) {

      System.out.println("\n==========================================");
      System.out.println("     Main Menu");
      System.out.println("==========================================");
      System.out.println("[1] Users Management");
      System.out.println("[2] Ofertas de Empleo");
      System.out.println("[0] Exit");
      System.out.println("==========================================");

      System.out.print("Option: ");
      String option = scanner.nextLine();

      switch (option) {

        case "1" -> userCli.start();

        case "2" -> ofertaCli.start();

        case "0" -> running = false;

        default -> System.out.println("Invalid option");
      }
    }

    scanner.close();
  }
}