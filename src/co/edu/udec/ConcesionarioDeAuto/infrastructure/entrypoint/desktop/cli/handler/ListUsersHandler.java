package edu.udec.ConcesionarioDeAuto.infrastructure.entrypoint.desktop.cli.handler;

import edu.udec.ConcesionarioDeAuto.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import edu.udec.ConcesionarioDeAuto.infrastructure.entrypoint.desktop.controller.UserController;
import edu.udec.ConcesionarioDeAuto.infrastructure.entrypoint.desktop.dto.UserResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

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