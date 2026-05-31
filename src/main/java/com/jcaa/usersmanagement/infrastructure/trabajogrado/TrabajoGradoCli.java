package com.jcaa.usersmanagement.infrastructure.trabajogrado;
import com.jcaa.usersmanagement.application.trabajogrado.TrabajoGradoService;
import com.jcaa.usersmanagement.domain.model.trabajogrado.TrabajoGrado;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
public class TrabajoGradoCli {
    private final TrabajoGradoService service;
    private final Scanner scanner;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public TrabajoGradoCli(TrabajoGradoService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }
    public TrabajoGradoService getService() { return service; }
    public void mostrarMenu() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n========================================");
            System.out.println("     GESTION DE TRABAJOS DE GRADO");
            System.out.println("========================================");
            System.out.println("[1] Listar todos");
            System.out.println("[2] Buscar por numero de orden");
            System.out.println("[3] Crear trabajo de grado");
            System.out.println("[4] Actualizar trabajo de grado");
            System.out.println("[5] Eliminar trabajo de grado");
            System.out.println("[0] Volver");
            System.out.print("Opcion: ");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1" -> listarTodos();
                case "2" -> buscarPorNumero();
                case "3" -> crear();
                case "4" -> actualizar();
                case "5" -> eliminar();
                case "0" -> continuar = false;
                default -> System.out.println("Opcion no valida.");
            }
        }
    }
    private void listarTodos() {
        List<TrabajoGrado> lista = service.listarTodos();
        if (lista.isEmpty()) { System.out.println("No hay trabajos registrados."); return; }
        lista.forEach(tg -> System.out.printf("Orden: %d | Tema: %s | Inicio: %s | Matricula: %s%n",
                tg.getNumeroOrden(), tg.getTema(), tg.getFechaInicio(), tg.getAlumnoMatricula()));
    }
    private void buscarPorNumero() {
        System.out.print("Numero de orden: ");
        Integer numero = Integer.parseInt(scanner.nextLine());
        try {
            TrabajoGrado tg = service.buscarPorNumeroOrden(numero);
            System.out.printf("Orden: %d | Tema: %s | Inicio: %s | Matricula: %s%n",
                    tg.getNumeroOrden(), tg.getTema(), tg.getFechaInicio(), tg.getAlumnoMatricula());
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }
    private void crear() {
        System.out.print("Numero de orden: ");
        Integer numero = Integer.parseInt(scanner.nextLine());
        System.out.print("Tema: ");
        String tema = scanner.nextLine();
        System.out.print("Fecha de inicio (yyyy-MM-dd): ");
        LocalDate fecha = LocalDate.parse(scanner.nextLine(), FMT);
        System.out.print("Matricula del alumno: ");
        String matricula = scanner.nextLine();
        try { service.crear(numero, tema, fecha, matricula); System.out.println("Creado exitosamente."); }
        catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }
    private void actualizar() {
        System.out.print("Numero de orden a actualizar: ");
        Integer numero = Integer.parseInt(scanner.nextLine());
        System.out.print("Nuevo tema: ");
        String tema = scanner.nextLine();
        System.out.print("Nueva fecha (yyyy-MM-dd): ");
        LocalDate fecha = LocalDate.parse(scanner.nextLine(), FMT);
        System.out.print("Nueva matricula: ");
        String matricula = scanner.nextLine();
        try { service.actualizar(numero, tema, fecha, matricula); System.out.println("Actualizado exitosamente."); }
        catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }
    private void eliminar() {
        System.out.print("Numero de orden a eliminar: ");
        Integer numero = Integer.parseInt(scanner.nextLine());
        try { service.eliminar(numero); System.out.println("Eliminado exitosamente."); }
        catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }
}
