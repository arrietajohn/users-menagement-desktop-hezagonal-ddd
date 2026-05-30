package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.handler;

import co.edu.udc.desechos_fabrica.user.domain.exception.UserNotFoundException;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.controller.UserController;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.UserResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindUserByEmailHandler implements OperationHandler {

  private final UserController userController;
  private final ConsoleIO console;
  private final UserResponsePrinter printer;

  @Override
  public void handle() {
    final String email = console.readRequired("User Email: ");
    try {
      final UserResponse user = userController.findUserByEmail(email);
      printer.print(user);
    } catch (final UserNotFoundException exception) {
      console.println("  Not found: " + exception.getMessage());
    }
  }
}