package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.*;
import com.jcaa.usersmanagement.domain.model.Vehiculomodel;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public final class VehiculoController {

    private final CreateVehiculoUseCase createVehiculoUseCase;
    private final UpdateVehiculoUseCase updateVehiculoUseCase;
    private final DeleteVehiculoUseCase deleteVehiculoUseCase;
    private final GetVehiculoByIdUseCase getVehiculoByIdUseCase;
    private final GetAllVehiculosUseCase getVehiculosUseCase;

    public void menu(final Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n========================================");
            System.out.println("         Gestión de Vehículos");
            System.out.println("========================================");
            System.out.println("[1] Listar todos los vehículos");
            System.out.println("[2] Buscar vehículo por bastidor");
            System.out.println("[3] Registrar vehículo");
            System.out.println("[4] Actualizar vehículo");
            System.out.println("[5] Eliminar vehículo");
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
        final List<Vehiculomodel> vehiculos = getVehiculosUseCase.execute();
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
            return;
        }
        System.out.println("\n--- Lista de Vehículos ---");
        for (final Vehiculomodel v : vehiculos) {
            printVehiculo(v);
        }
    }

    private void findById(final Scanner scanner) {
        System.out.print("Número de bastidor: ");
        final Integer id = Integer.parseInt(scanner.nextLine().trim());
        try {
            final Vehiculomodel v = getVehiculoByIdUseCase.execute(id);
            printVehiculo(v);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void create(final Scanner scanner) {
        System.out.print("Número de bastidor: ");
        final Integer id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Precio: ");
        final BigDecimal precio = new BigDecimal(scanner.nextLine().trim());
        System.out.print("Cilindrada: ");
        final String cilindrada = scanner.nextLine().trim();
        System.out.print("Potencia: ");
        final String potencia = scanner.nextLine().trim();
        System.out.print("Estado (disponible/vendido): ");
        final String estado = scanner.nextLine().trim();
        System.out.print("ID Modelo: ");
        final Integer idModelo = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("ID Servicio: ");
        final Integer idServicio = Integer.parseInt(scanner.nextLine().trim());

        try {
            final Vehiculomodel v = createVehiculoUseCase.execute(
                    id, precio, cilindrada, potencia, estado, idModelo, idServicio);
            System.out.println("Vehículo registrado exitosamente.");
            printVehiculo(v);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void update(final Scanner scanner) {
        System.out.print("Número de bastidor a actualizar: ");
        final Integer id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nuevo precio: ");
        final BigDecimal precio = new BigDecimal(scanner.nextLine().trim());
        System.out.print("Nueva cilindrada: ");
        final String cilindrada = scanner.nextLine().trim();
        System.out.print("Nueva potencia: ");
        final String potencia = scanner.nextLine().trim();
        System.out.print("Nuevo estado: ");
        final String estado = scanner.nextLine().trim();
        System.out.print("Nuevo ID Modelo: ");
        final Integer idModelo = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nuevo ID Servicio: ");
        final Integer idServicio = Integer.parseInt(scanner.nextLine().trim());

        try {
            final Vehiculomodel v = updateVehiculoUseCase.execute(
                    id, precio, cilindrada, potencia, estado, idModelo, idServicio);
            System.out.println("Vehículo actualizado exitosamente.");
            printVehiculo(v);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void delete(final Scanner scanner) {
        System.out.print("Número de bastidor a eliminar: ");
        final Integer id = Integer.parseInt(scanner.nextLine().trim());
        try {
            deleteVehiculoUseCase.execute(id);
            System.out.println("Vehículo eliminado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void printVehiculo(final Vehiculomodel v) {
        System.out.println("----------------------------------");
        System.out.println("Bastidor  : " + v.getIdBastidor());
        System.out.println("Precio    : " + v.getPrecio());
        System.out.println("Cilindrada: " + v.getCilindrada());
        System.out.println("Potencia  : " + v.getPotencia());
        System.out.println("Estado    : " + v.getEstado());
        System.out.println("ID Modelo : " + v.getIdModelo());
        System.out.println("ID Servicio: " + v.getIdServicio());
        System.out.println("----------------------------------");
    }
}