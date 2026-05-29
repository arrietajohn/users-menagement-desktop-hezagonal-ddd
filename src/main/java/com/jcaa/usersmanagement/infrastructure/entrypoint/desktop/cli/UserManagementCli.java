package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateCandidateHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateUserHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateVoterHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DeleteUserHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DeleteVoterHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.FindUserByIdHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.FindVoterHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListUsersHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListVotersHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.LoginHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.UpdateUserHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.UpdateVoterHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu.MenuOption;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
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
    final Map<MenuOption, OperationHandler> handlers = new HashMap<>();
    handlers.put(MenuOption.LIST_USERS,       new ListUsersHandler(userController, printer));
    handlers.put(MenuOption.FIND_USER,        new FindUserByIdHandler(userController, console, printer));
    handlers.put(MenuOption.CREATE_USER,      new CreateUserHandler(userController, console, printer));
    handlers.put(MenuOption.UPDATE_USER,      new UpdateUserHandler(userController, console, printer));
    handlers.put(MenuOption.DELETE_USER,      new DeleteUserHandler(userController, console));
    handlers.put(MenuOption.LOGIN,            new LoginHandler(userController, console, printer));
    handlers.put(MenuOption.CREATE_CANDIDATE, new CreateCandidateHandler(userController, console));
    handlers.put(MenuOption.CREATE_VOTER,     new CreateVoterHandler(userController, console));
    handlers.put(MenuOption.FIND_VOTER,       new FindVoterHandler(userController, console));
    handlers.put(MenuOption.LIST_VOTERS,      new ListVotersHandler(userController, console));
    handlers.put(MenuOption.UPDATE_VOTER,     new UpdateVoterHandler(userController, console));
    handlers.put(MenuOption.DELETE_VOTER,     new DeleteVoterHandler(userController, console));
    return handlers;
  }

  private void printMenu() {
    console.println();
    console.println(MENU_BORDER);
    console.println("    Main Menu");
    console.println(MENU_BORDER);
    for (final MenuOption option : MenuOption.values()) {
      console.printf("    [%d] %s%n", option.getNumber(), option.getDescription());
    }
    console.println(MENU_BORDER);
  }
}