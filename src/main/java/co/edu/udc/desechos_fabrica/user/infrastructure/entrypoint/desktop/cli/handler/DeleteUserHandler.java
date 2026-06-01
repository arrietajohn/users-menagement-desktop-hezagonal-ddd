package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.handler;

import co.edu.udc.desechos_fabrica.user.domain.exception.PermissionDeniedException;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserNotFoundException;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.controller.UserController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteUserHandler implements OperationHandler {

  private final UserController userController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    try {
      final String actorEmail = console.readRequired("Enter your email (the actor) to proceed: ");
      final String targetEmail = console.readRequired("Enter the email of the user to delete (the target): ");

      userController.deleteUser(actorEmail, targetEmail);
      console.println("\n  User deleted successfully.");

    } catch (final UserNotFoundException | PermissionDeniedException exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }
}
