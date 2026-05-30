package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli;

import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.handler.CreateUserHandler;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.handler.DeleteUserHandler;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.handler.FindUserByEmailHandler;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.handler.ListUsersHandler;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.handler.LoginHandler;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.handler.UpdateUserHandler;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.menu.MenuOption;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.util.UserMenuHandler;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.controller.UserController;
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
  private final ConsoleIO console;

  public void start() {
    console.println(BANNER);
    final UserResponsePrinter printer = new UserResponsePrinter(console);
    final UserMenuHandler menuHandler = new UserMenuHandler(console);
    runLoop(buildHandlers(printer, menuHandler));
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

  private Map<MenuOption, OperationHandler> buildHandlers(final UserResponsePrinter printer, final UserMenuHandler menuHandler) {
    return Map.of(
        MenuOption.LIST_USERS,  new ListUsersHandler(userController, printer),
        MenuOption.FIND_USER,   new FindUserByEmailHandler(userController, console, printer),
        MenuOption.CREATE_USER, new CreateUserHandler(userController, console, printer, menuHandler),
        MenuOption.UPDATE_USER, new UpdateUserHandler(userController, console, printer),
        MenuOption.DELETE_USER, new DeleteUserHandler(userController, console),
        MenuOption.LOGIN,       new LoginHandler(userController, console, printer));
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
