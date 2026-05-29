package com.jcaa.usersmanagement.infrastructure.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.UserNotFoundException;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.desktop.controller.UserController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteUserHandler implements OperationHandler {

  private final UserController userController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final String id = console.readRequired("User ID to delete: ");
    try {
      userController.deleteUser(id);
      console.println("  User deleted successfully.");
    } catch (final UserNotFoundException exception) {
      console.println("  Not found: " + exception.getMessage());
    }
  }
}