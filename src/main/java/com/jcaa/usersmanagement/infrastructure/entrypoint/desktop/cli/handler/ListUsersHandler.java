package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UserResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListUsersHandler implements OperationHandler {

  private final UserController userController;
  private final UserResponsePrinter printer;

  @Override
  public void handle() {
    final List<UserResponse> users = userController.listAllUsers();
    printer.printList(users);
  }
}