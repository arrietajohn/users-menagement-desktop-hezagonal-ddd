package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.application.service.ActualizarEstadoProyectoService;
import com.jcaa.usersmanagement.application.service.CambiarPromotorProyectoService;
import com.jcaa.usersmanagement.application.service.ProrrogarFechaFinService;
import com.jcaa.usersmanagement.domain.model.Proyecto;
import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;

import java.time.LocalDate;
import java.util.List;
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
            System.out.println("6. CONSULTAS Y REPORTES AVANZADOS (CEA - Unidad IV)");
            System.out.println("7. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del Scanner

            switch (opcion) {
                case 1 -> System.out.println("Funcionalidad CRUDL: Crear Proyecto (En desarrollo/Mantenimiento)...");
                case 2 -> System.out.println("Funcionalidad CRUDL: Buscar Proyecto (En desarrollo/Mantenimiento)...");
                case 3 -> System.out.println("Funcionalidad CRUDL: Listar Proyectos (En desarrollo/Mantenimiento)...");
                case 4 -> System.out.println("Funcionalidad CRUDL: Eliminar Proyecto (En desarrollo/Mantenimiento)...");
                case 5 -> mostrarSubmenuActualizaciones();
                case 6 -> mostrarMenuReportes();
                case 7 -> {
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
            LocalDate nuevaFecha = LocalDate.parse(fechaStr);
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

    // SUBMENÚ DE CONSULTAS AVANZADAS - UNIDAD 4 (

    private void mostrarMenuReportes() {
        System.out.println("\n--- SUBMENÚ: CONSULTAS Y REPORTES AVANZADOS (CEA) ---");
        System.out.println("1. Mostrar proyectos EN CURSO (CEA Consulta 1)");
        System.out.println("2. Ver proyectos completados por rango de fechas (CEA Consulta 7)");
        System.out.println("3. Listar proyectos asignados a un promotor por ID");
        System.out.println("4. Filtrar portafolio corporativo por estado operativo");
        System.out.println("5. Buscar proyectos por coincidencia de nombre/denominación");
        System.out.println("6. Volver al menú de proyectos");
        System.out.print("Seleccione un reporte: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del Scanner

        try {
            switch (opcion) {
                case 1 -> {
                    List<Proyecto> lista = container.getListarProyectosEnCursoService().execute();
                    imprimirTablaProyectos(lista);
                }
                case 2 -> {
                    System.out.print("Ingrese fecha inicial (AAAA-MM-DD): ");
                    LocalDate inicio = LocalDate.parse(scanner.nextLine());
                    System.out.print("Ingrese fecha límite (AAAA-MM-DD): ");
                    LocalDate fin = LocalDate.parse(scanner.nextLine());
                    List<Proyecto> lista = container.getBuscarProyectosPorRangoFechasService().execute(inicio, fin);
                    imprimirTablaProyectos(lista);
                }
                case 3 -> {
                    System.out.print("Ingrese el ID del promotor a auditar: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();
                    List<Proyecto> lista = container.getListarProyectosPorPromotorService().execute(id);
                    imprimirTablaProyectos(lista);
                }
                case 4 -> {
                    System.out.print("Ingrese el estado exacto (ej. SUSPENDIDO, PLANIFICADO): ");
                    String estado = scanner.nextLine();
                    List<Proyecto> lista = container.getFiltrarProyectosPorEstadoService().execute(estado);
                    imprimirTablaProyectos(lista);
                }
                case 5 -> {
                    System.out.print("Escriba el texto o palabra clave de búsqueda: ");
                    String termino = scanner.nextLine();
                    List<Proyecto> lista = container.getBuscarProyectosPorDenominacionService().execute(termino);
                    imprimirTablaProyectos(lista);
                }
                case 6 -> System.out.println("Regresando al menú de proyectos...");
                default -> System.out.println("Opción de reporte no disponible.");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error al procesar la consulta del CEA: " + e.getMessage());
        }
    }

    private void imprimirTablaProyectos(List<Proyecto> proyectos) {
        if (proyectos.isEmpty()) {
            System.out.println("❌ No se encontraron registros de proyectos que coincidan con los filtros ingresados.");
            return;
        }
        System.out.println("\n=====================================================================================================");
        System.out.printf("%-5s | %-30s | %-12s | %-12s | %-12s | %-10s\n", "ID", "DENOMINACIÓN COMERCIAL", "ESTADO", "F. INICIO", "F. FIN", "ID PROMOTOR");
        System.out.println("=====================================================================================================");
        for (Proyecto p : proyectos) {
            System.out.printf("%-5d | %-30s | %-12s | %-12s | %-12s | %-10d\n",
                    p.getIdProyecto(), p.getDenominacion(), p.getEstado(), p.getFechaInicio(), p.getFechaFin(), p.getIdPromotor());
        }
        System.out.println("=====================================================================================================");
        System.out.println("Resultados totales del reporte corporativo: " + proyectos.size());
    }
}