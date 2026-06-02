package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateDocumentoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateEmpleadoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateProyectoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateTareaHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateUserHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DeleteDocumentoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DeleteEmpleadoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DeleteProyectoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DeleteTareaHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DeleteUserHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.FindDocumentoByIdHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.FindEmpleadoByIdHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.FindProyectoByIdHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.FindTareaByIdHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.FindUserByIdHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListDocumentosHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListEmpleadosHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListProyectosHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListTareasHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListUsersHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.LoginHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.UpdateDocumentoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.UpdateEmpleadoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.UpdateProyectoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.UpdateTareaHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.UpdateUserHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.DocumentoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.EmpleadoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ProyectoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.TareaResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu.MenuOption;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.DocumentoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.EmpleadoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ProyectoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.TareaController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UserManagementCli {

  private static final String BANNER =
      """
      ==========================================
           Users Management System
      ==========================================""";

  private static final String MENU_BORDER = "  ==========================================";

  private final UserController userController;
  private final ProyectoController proyectoController;
  private final EmpleadoController empleadoController;
  private final TareaController tareaController;
  private final DocumentoController documentoController;
  private final ConsoleIO console;

  public void start() {
    console.println(BANNER);
    final UserResponsePrinter printer = new UserResponsePrinter(console);
    runLoop(buildHandlers(printer));
  }

  private void runLoop(final Map<MenuOption, OperationHandler> handlers) {
    boolean running = true;
    while (running) {
      printMenu();
      final int choice = console.readInt("\n  Option: ");
      final Optional<MenuOption> option = MenuOption.fromNumber(choice);

      if (option.isEmpty()) {
        console.println("  Invalid option. Please try again.");
      } else if (option.get() == MenuOption.EXIT) {
        console.println("\n  Goodbye!\n");
        running = false;
      } else {
        executeHandler(handlers, option.get());
      }
    }
  }

  private void executeHandler(
      final Map<MenuOption, OperationHandler> handlers, final MenuOption option) {
    try {
      handlers.get(option).handle();
    } catch (final ConstraintViolationException exception) {
      console.println("  Validation errors:");
      exception.getConstraintViolations()
          .forEach(violation -> console.println("    - " + violation.getMessage()));
    } catch (final RuntimeException exception) {
      console.println("  Unexpected error: " + exception.getMessage());
    }
  }

  private Map<MenuOption, OperationHandler> buildHandlers(final UserResponsePrinter printer) {
    final ProyectoResponsePrinter proyectoPrinter = new ProyectoResponsePrinter(console);
    final EmpleadoResponsePrinter empleadoPrinter = new EmpleadoResponsePrinter(console);
    final TareaResponsePrinter tareaPrinter = new TareaResponsePrinter(console);
    final DocumentoResponsePrinter documentoPrinter = new DocumentoResponsePrinter(console);
    return Map.ofEntries(
        Map.entry(MenuOption.LIST_USERS,        new ListUsersHandler(userController, printer)),
        Map.entry(MenuOption.FIND_USER,         new FindUserByIdHandler(userController, console, printer)),
        Map.entry(MenuOption.CREATE_USER,       new CreateUserHandler(userController, console, printer)),
        Map.entry(MenuOption.UPDATE_USER,       new UpdateUserHandler(userController, console, printer)),
        Map.entry(MenuOption.DELETE_USER,       new DeleteUserHandler(userController, console)),
        Map.entry(MenuOption.LOGIN,             new LoginHandler(userController, console, printer)),
        Map.entry(MenuOption.LIST_PROYECTOS,    new ListProyectosHandler(proyectoController, proyectoPrinter)),
        Map.entry(MenuOption.FIND_PROYECTO,     new FindProyectoByIdHandler(proyectoController, console, proyectoPrinter)),
        Map.entry(MenuOption.CREATE_PROYECTO,   new CreateProyectoHandler(proyectoController, console, proyectoPrinter)),
        Map.entry(MenuOption.UPDATE_PROYECTO,   new UpdateProyectoHandler(proyectoController, console, proyectoPrinter)),
        Map.entry(MenuOption.DELETE_PROYECTO,   new DeleteProyectoHandler(proyectoController, console)),
        Map.entry(MenuOption.LIST_EMPLEADOS,    new ListEmpleadosHandler(empleadoController, empleadoPrinter)),
        Map.entry(MenuOption.FIND_EMPLEADO,     new FindEmpleadoByIdHandler(empleadoController, console, empleadoPrinter)),
        Map.entry(MenuOption.CREATE_EMPLEADO,   new CreateEmpleadoHandler(empleadoController, console, empleadoPrinter)),
        Map.entry(MenuOption.UPDATE_EMPLEADO,   new UpdateEmpleadoHandler(empleadoController, console, empleadoPrinter)),
        Map.entry(MenuOption.DELETE_EMPLEADO,   new DeleteEmpleadoHandler(empleadoController, console)),
        Map.entry(MenuOption.LIST_TAREAS,       new ListTareasHandler(tareaController, tareaPrinter)),
        Map.entry(MenuOption.FIND_TAREA,        new FindTareaByIdHandler(tareaController, console, tareaPrinter)),
        Map.entry(MenuOption.CREATE_TAREA,      new CreateTareaHandler(tareaController, console, tareaPrinter)),
        Map.entry(MenuOption.UPDATE_TAREA,      new UpdateTareaHandler(tareaController, console, tareaPrinter)),
        Map.entry(MenuOption.DELETE_TAREA,      new DeleteTareaHandler(tareaController, console)),
        Map.entry(MenuOption.LIST_DOCUMENTOS,   new ListDocumentosHandler(documentoController, documentoPrinter)),
        Map.entry(MenuOption.FIND_DOCUMENTO,    new FindDocumentoByIdHandler(documentoController, console, documentoPrinter)),
        Map.entry(MenuOption.CREATE_DOCUMENTO,  new CreateDocumentoHandler(documentoController, console, documentoPrinter)),
        Map.entry(MenuOption.UPDATE_DOCUMENTO,  new UpdateDocumentoHandler(documentoController, console, documentoPrinter)),
        Map.entry(MenuOption.DELETE_DOCUMENTO,  new DeleteDocumentoHandler(documentoController, console)));
  }

  private void printMenu() {
    console.println();
    // VIOLACIÓN Regla 10: se usa el String literal "==================..." hardcodeado directamente
    // en vez de reutilizar la constante MENU_BORDER que ya está definida en la misma clase.
    console.println(MENU_BORDER);
    console.println("    Main Menu");
    console.println(MENU_BORDER);
    for (final MenuOption option : MenuOption.values()) {
      console.printf("    [%d] %s%n", option.getNumber(), option.getDescription());
    }
    console.println(MENU_BORDER);
  }
}