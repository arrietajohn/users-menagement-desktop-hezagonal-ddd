package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.handler;

import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserStatus;
import co.edu.udc.desechos_fabrica.user.domain.exception.PermissionDeniedException;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserNotFoundException;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserAlreadyExistsException;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import co.edu.udc.desechos_fabrica.user.domain.service.UserRoleManager;
import co.edu.udc.desechos_fabrica.user.domain.service.UserRoleManagerService;
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
    try {
      final String actorEmail = console.readRequired("Enter your email (the actor) to proceed: ");
      final String targetEmail = console.readRequired("Enter the email of the user to update (the target): ");

      final UserModel actor = userController.findUserModelByEmail(actorEmail);
      final UserModel targetUser = userController.findUserModelByEmail(targetEmail);
      final UserRoleManager roleManager = new UserRoleManagerService();

      roleManager.checkUpdatePermissions(actor, targetUser, null); // `null` para chequear permiso general

      console.println("\nEnter the new data for the user:");
      final String newFirstName = console.readRequired("New first name: ");
      final String newLastName = console.readRequired("New last name: ");
      final String newEmail = console.readRequired("New email: ");
      final String newPassword = console.readOptional("New password (leave blank to keep current): ");
      
      final UserMenuHandler menuHandler = new UserMenuHandler(console);
      final UserRole newRole = menuHandler.selectRoleFromConsole();
      final UserStatus newStatus = menuHandler.selectStatusFromConsole();

      roleManager.checkUpdatePermissions(actor, targetUser, newRole);

      final UserResponse updated = userController.updateUser(
          new UpdateUserRequest(
              actorEmail,
              targetEmail,
              newFirstName,
              newLastName,
              newEmail,
              newPassword.isBlank() ? null : newPassword,
              newRole.name(),
              newStatus.name(),
              null
          ));

      console.println("\n  User updated successfully.");
      printer.print(updated);

    } catch (final UserNotFoundException | PermissionDeniedException | UserAlreadyExistsException exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }
}
