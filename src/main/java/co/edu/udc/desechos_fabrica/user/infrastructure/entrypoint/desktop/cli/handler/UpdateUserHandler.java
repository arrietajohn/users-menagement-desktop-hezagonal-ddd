package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.handler;

import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserStatus;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserNotFoundException;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserAlreadyExistsException;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.util.UserMenuHandler;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.controller.UserController;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.UpdateUserRequest;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.UserResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateUserHandler implements OperationHandler {

  private final UserController userController;
  private final ConsoleIO console;
  private final UserResponsePrinter printer;

  @Override
  public void handle() {

    final String actorEmail = console.readRequired("Enter your administrator email to proceed: ");
    final UserResponse actor = userController.findUserByEmail(actorEmail);
    final UserRole actorRole = UserRole.fromString(actor.role());

    final String currentEmail = console.readRequired("Current email of user to update           : ");
    final String firstName = console.readRequired("New first name                               : ");
    final String lastName = console.readRequired("New last name                                 : ");
    final String newEmail = console.readRequired("New email                                     : ");
    final String password = console.readOptional("New password (leave blank to keep current)    : ");

    final UserMenuHandler userMenuHandler = new UserMenuHandler(console);
    UserRole role = null;
    UserStatus status = null;

    if (actorRole == UserRole.ADMIN || actorRole == UserRole.REVIEWER) {
      role = userMenuHandler.selectRoleFromConsole();
      status = userMenuHandler.selectStatusFromConsole();
    } else if (actorRole == UserRole.ENTERPRISE_ADMIN) {
      console.println("\nNote: You cannot change the user role. It will remain unchanged.");
      status = userMenuHandler.selectStatusFromConsole();
    } else {
      console.println("\nNote: You cannot change the user role or status. It will remain unchanged.");
    }

    try {
      final UserResponse updated = userController.updateUser(
              new UpdateUserRequest(
                      currentEmail,
                      firstName,
                      lastName,
                      newEmail,
                      password.isBlank() ? null : password,
                      role != null ? role.name() : null,
                      status != null ? status.name() : null));
      console.println("\n  User updated successfully.");
      printer.print(updated);
    } catch (final UserNotFoundException exception) {
      console.println("  Not found: " + exception.getMessage());
    } catch (final UserAlreadyExistsException exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }
}
