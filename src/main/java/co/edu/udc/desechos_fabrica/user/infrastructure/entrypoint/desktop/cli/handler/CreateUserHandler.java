package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.handler;

import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserAlreadyExistsException;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.controller.UserController;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.CreateUserRequest;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.UserResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateUserHandler implements OperationHandler {

  private final UserController userController;
  private final ConsoleIO console;
  private final UserResponsePrinter printer;


  @Override
  public void handle() {
    final String firstName = console.readRequired("FirstName : ");
    final String lastName = console.readRequired("LastName  : ");
    final String email = console.readRequired("Email     : ");
    final String password = console.readRequired("Password  : ");

    try {
      final UserResponse created =
              userController.createUser(
                      new CreateUserRequest(firstName, lastName, email, password, UserRole.MEMBER));
      console.println("\n  User created successfully.");
      printer.print(created);
    } catch (final UserAlreadyExistsException exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }
}
