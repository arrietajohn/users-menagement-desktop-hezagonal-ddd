package com.jcaa.usersmanagement.infrastructure.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.desktop.cli.io.UserResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.desktop.controller.UserController;
import com.jcaa.usersmanagement.infrastructure.desktop.dto.UserResponse;
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