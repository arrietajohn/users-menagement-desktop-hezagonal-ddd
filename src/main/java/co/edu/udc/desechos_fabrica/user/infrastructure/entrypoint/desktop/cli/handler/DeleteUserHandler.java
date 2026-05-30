package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.handler;

import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserNotFoundException;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.controller.UserController;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.UserResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteUserHandler implements OperationHandler {

  private final UserController userController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final String actorEmail = console.readRequired("Enter your email (as administrator) to proceed: ");
    final String email = console.readRequired("User Email to delete: ");

    final UserResponse actor = userController.findUserByEmail(actorEmail);
    final UserResponse emailToDelete = userController.findUserByEmail(email);

    final UserRole actorRole = UserRole.fromString(actor.role());
    final UserRole emailToDeleteRole = UserRole.fromString(emailToDelete.role());

    if (deletePermissions(actorRole, emailToDeleteRole)) {
      console.println("\n  Error: You do not have permission to delete this user.");
      return;
    }
    try {
      userController.deleteUser(email);
      console.println("  User deleted successfully.");
    } catch (final UserNotFoundException exception) {
      console.println("  Not found: " + exception.getMessage());
    }
  }

  public boolean deletePermissions(UserRole actorRole, UserRole emailToDeleteRole) {
    return switch (actorRole) {
      case ADMIN -> false;
      case REVIEWER -> (emailToDeleteRole == UserRole.ADMIN || emailToDeleteRole == UserRole.REVIEWER);
      case ENTERPRISE_ADMIN ->
              (emailToDeleteRole == UserRole.ADMIN || emailToDeleteRole == UserRole.REVIEWER || emailToDeleteRole == UserRole.ENTERPRISE_ADMIN);
      case MEMBER -> true;
    };
  }
}