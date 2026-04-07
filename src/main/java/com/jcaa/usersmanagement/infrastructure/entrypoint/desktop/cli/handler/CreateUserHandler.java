package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.UserAlreadyExistsException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateUserRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UserResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateUserHandler implements OperationHandler {

  private final UserController userController;
  private final ConsoleIO console;
  private final UserResponsePrinter printer;

  @Override
  public void handle() {
    final String id       = console.readRequired("ID                              : ");
    final String name     = console.readRequired("Name                            : ");
    final String email    = console.readRequired("Email                           : ");
    final String password = console.readRequired("Password                        : ");
    final String role     = console.readRequired("Role (ADMIN / MEMBER / REVIEWER): ");

    try {
      final UserResponse created =
          userController.createUser(new CreateUserRequest(id, name, email, password, role));
      console.println("\n  User created successfully.");
      printer.print(created);
    } catch (final UserAlreadyExistsException exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }
}