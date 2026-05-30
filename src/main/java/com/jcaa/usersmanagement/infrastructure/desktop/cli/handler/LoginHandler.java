package com.jcaa.usersmanagement.infrastructure.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.InvalidCredentialsException;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.io.UserResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.desktop.controller.UserController;
import com.jcaa.usersmanagement.infrastructure.desktop.dto.LoginRequest;
import com.jcaa.usersmanagement.infrastructure.desktop.dto.UserResponse;
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