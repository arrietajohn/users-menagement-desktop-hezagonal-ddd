package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.*;
import com.jcaa.usersmanagement.domain.model.ClienteModel;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public final class ClienteController {

    private final CreateClienteUseCase createClienteUseCase;
    private final UpdateClienteUseCase updateClienteUseCase;
    private final DeleteClienteUseCase deleteClienteUseCase;
    private final GetClienteByIdUseCase getClienteByIdUseCase;
    private final GetAllClientesUseCase getAllClientesUseCase;

    public void menu(final Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n========================================");
            System.out.println("         Gestión de Clientes");
            System.out.println("========================================");
            System.out.println("[1] Listar todos los clientes");
            System.out.println("[2] Buscar cliente por ID");
            System.out.println("[3] Registrar cliente");
            System.out.println("[4] Actualizar cliente");
            System.out.println("[5] Eliminar cliente");
            System.out.println("[0] Volver al menú principal");
            System.out.println("========================================");
            System.out.print("Opción: ");
            final String option = scanner.nextLine().trim();

            switch (option) {
                case "1" -> listAll();
                case "2" -> findById(scanner);
                case "3" -> create(scanner);
                case "4" -> update(scanner);
                case "5" -> delete(scanner);
                case "0" -> running = false;
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void listAll() {
        final List<ClienteModel> clientes = getAllClientesUseCase.execute();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        System.out.println("\n--- Lista de Clientes ---");
        for (final ClienteModel c : clientes) {
            printCliente(c);
        }
    }

    private void findById(final Scanner scanner) {
        System.out.print("ID del cliente: ");
        final Integer id = Integer.parseInt(scanner.nextLine().trim());
        try {
            printCliente(getClienteByIdUseCase.execute(id));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void create(final Scanner scanner) {
        System.out.print("Nombre: ");
        final String nombre = scanner.nextLine().trim();
        System.out.print("Apellido: ");
        final String apellido = scanner.nextLine().trim();
        System.out.print("Email: ");
        final String email = scanner.nextLine().trim();
        System.out.print("Teléfono: ");
        final String telefono = scanner.nextLine().trim();
        System.out.print("Dirección: ");
        final String direccion = scanner.nextLine().trim();
        try {
            final ClienteModel c = createClienteUseCase.execute(nombre, apellido, email, telefono, direccion);
            System.out.println("Cliente registrado exitosamente.");
            printCliente(c);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void update(final Scanner scanner) {
        System.out.print("ID del cliente a actualizar: ");
        final Integer id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nuevo nombre: ");
        final String nombre = scanner.nextLine().trim();
        System.out.print("Nuevo apellido: ");
        final String apellido = scanner.nextLine().trim();
        System.out.print("Nuevo email: ");
        final String email = scanner.nextLine().trim();
        System.out.print("Nuevo teléfono: ");
        final String telefono = scanner.nextLine().trim();
        System.out.print("Nueva dirección: ");
        final String direccion = scanner.nextLine().trim();
        try {
            final ClienteModel c = updateClienteUseCase.execute(id, nombre, apellido, email, telefono, direccion);
            System.out.println("Cliente actualizado exitosamente.");
            printCliente(c);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void delete(final Scanner scanner) {
        System.out.print("ID del cliente a eliminar: ");
        final Integer id = Integer.parseInt(scanner.nextLine().trim());
        try {
            deleteClienteUseCase.execute(id);
            System.out.println("Cliente eliminado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void printCliente(final ClienteModel c) {
        System.out.println("----------------------------------");
        System.out.println("ID        : " + c.getIdCliente());
        System.out.println("Nombre    : " + c.getNombre());
        System.out.println("Apellido  : " + c.getApellido());
        System.out.println("Email     : " + c.getEmail());
        System.out.println("Teléfono  : " + c.getTelefono());
        System.out.println("Dirección : " + c.getDireccion());
        System.out.println("----------------------------------");
    }
}
