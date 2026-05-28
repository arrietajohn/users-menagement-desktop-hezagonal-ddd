package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.handler;

import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.controller.UserController;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.dto.UserResponse;
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