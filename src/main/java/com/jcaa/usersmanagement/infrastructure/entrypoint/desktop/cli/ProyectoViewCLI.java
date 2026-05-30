package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.application.service.ActualizarEstadoProyectoService;
import com.jcaa.usersmanagement.application.service.CambiarPromotorProyectoService;
import com.jcaa.usersmanagement.application.service.ProrrogarFechaFinService;
import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;
import java.util.Scanner;

public class ProyectoViewCLI {
    private final DependencyContainer container;
    private final Scanner scanner;

    public ProyectoViewCLI(DependencyContainer container) {
        this.container = container;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- MÓDULO DE PROYECTOS ---");
            System.out.println("1. Crear Proyecto (CRUDL)");
            System.out.println("2. Buscar Proyecto por ID");
            System.out.println("3. Listar Todos los Proyectos");
            System.out.println("4. Eliminar Proyecto");
            System.out.println("5. ACTUALIZACIONES AVANZADAS (Estado, Fecha, Promotor)");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del Scanner

            switch (opcion) {
                case 1 -> System.out.println("Funcionalidad CRUDL: Crear Proyecto (En desarrollo/Mantenimiento)...");
                case 2 -> System.out.println("Funcionalidad CRUDL: Buscar Proyecto (En desarrollo/Mantenimiento)...");
                case 3 -> System.out.println("Funcionalidad CRUDL: Listar Proyectos (En desarrollo/Mantenimiento)...");
                case 4 -> System.out.println("Funcionalidad CRUDL: Eliminar Proyecto (En desarrollo/Mantenimiento)...");
                case 5 -> mostrarSubmenuActualizaciones();
                case 6 -> {
                    System.out.println("Saliendo del módulo de proyectos...");
                    salir = true;
                }
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    private void mostrarSubmenuActualizaciones() {
        System.out.println("\n--- GESTIÓN AVANZADA DE PROYECTO ---");
        System.out.println("1. Cambiar Estado del Proyecto");
        System.out.println("2. Prorrogar Fecha Fin (Extender Plazo)");
        System.out.println("3. Cambiar Promotor Asociado");
        System.out.println("4. Volver al menú de proyectos");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1 -> ejecutarActualizarEstado();
            case 2 -> ejecutarProrrogarFecha();
            case 3 -> ejecutarCambiarPromotor();
            case 4 -> System.out.println("Regresando al menú de proyectos...");
            default -> System.out.println("Opción no válida.");
        }
    }

    private void ejecutarActualizarEstado() {
        System.out.print("Ingrese el ID del proyecto: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese el nuevo estado (ej. ACTIVO, SUSPENDIDO, FINALIZADO): ");
        String nuevoEstado = scanner.nextLine();

        try {
            // Obtenemos el servicio directamente desde el contenedor centralizado
            ActualizarEstadoProyectoService service = container.getActualizarEstadoProyectoService();
            service.execute(id, nuevoEstado);
            System.out.println("¡Estado del proyecto actualizado con éxito en la base de datos!");
        } catch (Exception e) {
            System.out.println("Error al actualizar el estado: " + e.getMessage());
        }
    }

    private void ejecutarProrrogarFecha() {
        System.out.print("Ingrese el ID del proyecto: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese la nueva fecha fin (formato AAAA-MM-DD): ");
        String fechaStr = scanner.nextLine();

        try {
            java.time.LocalDate nuevaFecha = java.time.LocalDate.parse(fechaStr);
            ProrrogarFechaFinService service = container.getProrrogarFechaFinService();
            service.execute(id, nuevaFecha);
            System.out.println("¡Fecha de finalización prorrogada con éxito en la base de datos!");
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Formato de fecha incorrecto. Asegúrese de usar AAAA-MM-DD.");
        } catch (Exception e) {
            System.out.println("Error al prorrogar la fecha: " + e.getMessage());
        }
    }

    private void ejecutarCambiarPromotor() {
        System.out.print("Ingrese el ID del proyecto: ");
        Long id = scanner.nextLong();
        System.out.print("Ingrese el ID del nuevo promotor (Usuario): ");
        Long idPromotor = scanner.nextLong();
        scanner.nextLine(); // Limpiar buffer

        try {
            CambiarPromotorProyectoService service = container.getCambiarPromotorProyectoService();
            service.execute(id, idPromotor);
            System.out.println("¡Promotor del proyecto modificado con éxito en la base de datos!");
        } catch (Exception e) {
            System.out.println("Error al cambiar el promotor: " + e.getMessage());
        }
    }
}