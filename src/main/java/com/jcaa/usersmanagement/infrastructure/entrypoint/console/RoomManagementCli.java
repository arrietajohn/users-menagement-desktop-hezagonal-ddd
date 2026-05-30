package com.jcaa.usersmanagement.infrastructure.entrypoint.console;

import com.jcaa.usersmanagement.domain.model.Room;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.RoomController;

import java.util.List;
import java.util.Scanner;

public class RoomManagementCli {

    private final RoomController roomController;
    private final Scanner scanner;

    public RoomManagementCli(RoomController roomController) {
        this.roomController = roomController;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n=======================================");
            System.out.println("     GENTIÓN DE HABITACIONES (CRUD)    ");
            System.out.println("=======================================");
            System.out.println("1. Registrar nueva habitación");
            System.out.println("2. Actualizar habitación");
            System.out.println("3. Eliminar habitación");
            System.out.println("4. Listar habitaciones disponibles");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int option = readInt();

            switch (option) {
                case 1 -> createRoom();
                case 2 -> updateRoom();
                case 3 -> deleteRoom();
                case 4 -> listAvailableRooms();
                case 5 -> {
                    System.out.println("Regresando...");
                    running = false;
                }
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    private void createRoom() {
        System.out.println("\n--- REGISTRAR HABITACIÓN ---");
        System.out.print("Número de Habitación: ");
        String roomNumber = scanner.nextLine();
        System.out.print("Tipo (Simple, Doble, Suite): ");
        String type = scanner.nextLine();
        System.out.print("Precio por Noche: ");
        double price = readDouble();
        System.out.print("ID del Hotel: ");
        Long hotelId = readLong();

        try {
            Room created = roomController.createRoom(roomNumber, type, price, hotelId);
            System.out.println("Habitación registrada con éxito. ID asignado: " + created.getId());
        } catch (Exception e) {
            System.out.println("Error al registrar: " + e.getMessage());
        }
    }

    private void updateRoom() {
        System.out.println("\n--- ACTUALIZAR HABITACIÓN ---");
        System.out.print("Ingrese el ID de la habitación a modificar: ");
        Long id = readLong();
        System.out.print("Nuevo Número de Habitación: ");
        String roomNumber = scanner.nextLine();
        System.out.print("Nuevo Tipo: ");
        String type = scanner.nextLine();
        System.out.print("Nuevo Precio por Noche: ");
        double price = readDouble();
        System.out.print("¿Está disponible? (true/false): ");
        boolean isAvailable = Boolean.parseBoolean(scanner.nextLine());
        System.out.print("ID del Hotel: ");
        Long hotelId = readLong();

        try {
            roomController.updateRoom(id, roomNumber, type, price, isAvailable, hotelId);
            System.out.println("Habitación actualizada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    private void deleteRoom() {
        System.out.println("\n--- ELIMINAR HABITACIÓN ---");
        System.out.print("Ingrese el ID de la habitación a eliminar: ");
        Long id = readLong();

        try {
            roomController.deleteRoom(id);
            System.out.println("Habitación eliminada con éxito.");
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    private void listAvailableRooms() {
        System.out.println("\n--- HABITACIONES DISPONIBLES ---");
        try {
            List<Room> rooms = roomController.getAvailableRooms();
            if (rooms.isEmpty()) {
                System.out.println("⚠️ No hay habitaciones disponibles en este momento.");
            } else {
                System.out.printf("%-5s %-12s %-12s %-12s %-10s\n", "ID", "Número", "Tipo", "Precio/Noche", "Hotel ID");
                System.out.println("-------------------------------------------------------------");
                for (Room r : rooms) {
                    System.out.printf("%-5d %-12s %-12s $%-11.2f %-10d\n",
                            r.getId(), r.getRoomNumber(), r.getType(), r.getPricePerNight(), r.getHotelId());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
    }

    // Métodos auxiliares robustos para evitar que la app se rompa si digitan letras por números
    private int readInt() {
        try {
            int value = Integer.parseInt(scanner.nextLine());
            return value;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Monto inválido. Ingrese un precio decimal válido: ");
            }
        }
    }

    private Long readLong() {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("ID inválido. Ingrese un número entero válido: ");
            }
        }
    }
}