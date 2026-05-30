package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.handler;

import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserAlreadyExistsException;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.controller.UserController;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.CreateUserRequest;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.UserResponse;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.util.UserMenuHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateUserHandler implements OperationHandler {

  private final UserController userController;
  private final ConsoleIO console;
  private final UserResponsePrinter printer;
  private final UserMenuHandler userMenuHandler;


  @Override
  public void handle() {
    final String firstName = console.readRequired("FirstName : ");
    final String lastName = console.readRequired("LastName  : ");
    final String email = console.readRequired("Email     : ");
    final String password = console.readRequired("Password  : ");

    final UserRole selectedRole = userMenuHandler.selectRoleFromConsole();

    try {
      final UserResponse created =
              userController.createUser(
                      new CreateUserRequest(firstName, lastName, email, password, selectedRole.name()));
      console.println("\n  User created successfully.");
      printer.print(created);
    } catch (final UserAlreadyExistsException exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }
}
