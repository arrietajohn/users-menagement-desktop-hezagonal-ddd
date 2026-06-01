package edu.udec.ConcesionarioDeAuto.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.InvalidCredentialsException;
import edu.udec.ConcesionarioDeAuto.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import edu.udec.ConcesionarioDeAuto.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import edu.udec.ConcesionarioDeAuto.infrastructure.entrypoint.desktop.controller.UserController;
import edu.udec.ConcesionarioDeAuto.infrastructure.entrypoint.desktop.dto.LoginRequest;
import edu.udec.ConcesionarioDeAuto.infrastructure.entrypoint.desktop.dto.UserResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class LoginHandler implements OperationHandler {

  private final UserController userController;
  private final ConsoleIO console;
  private final UserResponsePrinter printer;

  @Override
  public void handle() {
    final String email    = console.readRequired("Email   : ");
    final String password = console.readRequired("Password: ");
    try {
      final UserResponse user = userController.login(new LoginRequest(email, password));
      console.println("\n  Login successful. Welcome!");
      printer.print(user);
    } catch (final InvalidCredentialsException exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }
}