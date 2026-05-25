package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;

public final class CreateCandidateHandler implements OperationHandler {

  private final UserController userController;
  private final ConsoleIO console;

  public CreateCandidateHandler(final UserController userController, final ConsoleIO console) {
    this.userController = userController;
    this.console = console;
  }

  @Override
  public void handle() {

    final String dni = console.readRequired("DNI  : ");
    final String name = console.readRequired("Name : ");
    final String party = console.readRequired("Party: ");

    userController.createCandidate(dni, name, party);

    console.println("\n  Candidate created successfully.");
  }
}
