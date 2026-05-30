package com.jcaa.usersmanagement.infrastructure.desktop.cli;

import com.jcaa.usersmanagement.infrastructure.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.handler.doctor.CreateDoctorHandler;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.handler.doctor.DeleteDoctorHandler;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.handler.doctor.FindDoctorByIdHandler;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.handler.doctor.ListDoctorsHandler;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.handler.doctor.UpdateDoctorHandler;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.io.DoctorResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.menu.DoctorMenuOption;
import com.jcaa.usersmanagement.infrastructure.desktop.controller.doctor.DoctorController;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DoctorManagementCli {

    private static final String BANNER =
            """
            ==========================================
                 Sistema de Gestión de Médicos
            ==========================================""";

    private static final String MENU_BORDER = "  ==========================================";

    private final DoctorController doctorController;
    private final ConsoleIO console;

    public void start() {
        console.println(BANNER);
        final DoctorResponsePrinter printer = new DoctorResponsePrinter(console);
        runLoop(buildHandlers(printer));
    }

    private void runLoop(final Map<DoctorMenuOption, OperationHandler> handlers) {
        boolean running = true;
        while (running) {
            printMenu();
            final int choice = console.readInt("\n  Opción: ");
            final Optional<DoctorMenuOption> option = DoctorMenuOption.fromNumber(choice);

            if (option.isEmpty()) {
                console.println("  Opción inválida. Por favor, intente de nuevo.");
            } else if (option.get() == DoctorMenuOption.EXIT) {
                console.println("\n  ¡Hasta luego!\n");
                running = false;
            } else {
                executeHandler(handlers, option.get());
            }
        }
    }

    private void executeHandler(
            final Map<DoctorMenuOption, OperationHandler> handlers, final DoctorMenuOption option) {
        try {
            handlers.get(option).handle();
        } catch (final ConstraintViolationException exception) {
            console.println("  Errores de validación:");
            exception.getConstraintViolations()
                    .forEach(violation -> console.println("    - " + violation.getMessage()));
        } catch (final RuntimeException exception) {
            console.println("  Error inesperado: " + exception.getMessage());
        }
    }

    private Map<DoctorMenuOption, OperationHandler> buildHandlers(final DoctorResponsePrinter printer) {
        return Map.of(
                DoctorMenuOption.LIST_DOCTORS,  new ListDoctorsHandler(doctorController, printer),
                DoctorMenuOption.FIND_DOCTOR,   new FindDoctorByIdHandler(doctorController, console, printer),
                DoctorMenuOption.CREATE_DOCTOR, new CreateDoctorHandler(doctorController, console, printer),
                DoctorMenuOption.UPDATE_DOCTOR, new UpdateDoctorHandler(doctorController, console, printer),
                DoctorMenuOption.DELETE_DOCTOR, new DeleteDoctorHandler(doctorController, console));
    }

    private void printMenu() {
        console.println();
        console.println(MENU_BORDER);
        console.println("    Menú Principal de Médicos");
        console.println(MENU_BORDER);
        for (final DoctorMenuOption option : DoctorMenuOption.values()) {
            console.printf("    [%d] %s%n", option.getNumber(), option.getDescription());
        }
        console.println(MENU_BORDER);
    }
}