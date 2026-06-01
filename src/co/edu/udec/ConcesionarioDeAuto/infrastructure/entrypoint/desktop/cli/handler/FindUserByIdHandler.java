package edu.udec.ConcesionarioDeAuto.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.UserNotFoundException;
import edu.udec.ConcesionarioDeAuto.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import edu.udec.ConcesionarioDeAuto.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import edu.udec.ConcesionarioDeAuto.infrastructure.entrypoint.desktop.controller.UserController;
import edu.udec.ConcesionarioDeAuto.infrastructure.entrypoint.desktop.dto.UserResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindUserByIdHandler implements OperationHandler {

  private final UserController userController;
  private final ConsoleIO console;
  private final UserResponsePrinter printer;

  @Override
  public void handle() {
    final String id = console.readRequired("User ID: ");
    try {
      final UserResponse user = userController.findUserById(id);
      printer.print(user);
    } catch (final UserNotFoundException exception) {
      console.println("  Not found: " + exception.getMessage());
    }
  }
}