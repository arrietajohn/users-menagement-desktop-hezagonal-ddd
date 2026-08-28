package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.ofertaempleo.cli;

import com.jcaa.usersmanagement.application.service.dto.command.CreateOfertaEmpleoCommand;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.ofertaempleo.OfertaEmpleoController;

import java.math.BigDecimal;
import java.util.Scanner;

public class OfertaEmpleoCli {

    private final OfertaEmpleoController controller;
    private final Scanner scanner;

    public OfertaEmpleoCli(OfertaEmpleoController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n=== OFERTAS EMPLEO ===");
            System.out.println("[1] Crear oferta");
            System.out.println("[2] Listar ofertas");
            System.out.println("[0] Salir");

            System.out.print("Opción: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1" -> create();
                case "2" -> list();
                case "0" -> running = false;
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private void create() {

        System.out.print("ID: ");
        String id = scanner.nextLine();

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        System.out.print("Empresa: ");
        String empresa = scanner.nextLine();

        System.out.print("Ubicación: ");
        String ubicacion = scanner.nextLine();

        System.out.print("Salario: ");
        BigDecimal salario = new BigDecimal(scanner.nextLine());

        System.out.print("Estado: ");
        String estado = scanner.nextLine();

        controller.create(
                new CreateOfertaEmpleoCommand(
                        id,
                        titulo,
                        descripcion,
                        empresa,
                        ubicacion,
                        salario,
                        estado
                )
        );

        System.out.println("Oferta creada correctamente");
    }

    private void list() {
        controller.getAll().forEach(oferta -> System.out.println(oferta.toString()));
    }
}