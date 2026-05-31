package com.jcaa.usersmanagement;
import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.UserManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.trabajogrado.TrabajoGradoCli;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;
public final class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(final String[] args) {
        log.info("Starting Users Management System...");
        final DependencyContainer container = new DependencyContainer();
        try (final Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("\n========================================");
                System.out.println("         MENU PRINCIPAL");
                System.out.println("========================================");
                System.out.println("[1] Gestion de Usuarios");
                System.out.println("[2] Gestion de Trabajos de Grado");
                System.out.println("[0] Salir");
                System.out.print("Opcion: ");
                String opcion = scanner.nextLine();
                switch (opcion) {
                    case "1" -> new UserManagementCli(container.userController(), new ConsoleIO(scanner, System.out)).start();
                    case "2" -> container.trabajoGradoCli(scanner).mostrarMenu();
                    case "0" -> { running = false; System.out.println("Goodbye!"); }
                    default -> System.out.println("Opcion no valida.");
                }
            }
        }
    }
}
