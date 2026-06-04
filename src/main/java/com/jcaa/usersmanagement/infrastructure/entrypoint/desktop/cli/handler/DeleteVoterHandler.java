package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;

public class DeleteVoterHandler implements OperationHandler {

    private final UserController userController;
    private final ConsoleIO console;

    public DeleteVoterHandler(UserController userController, ConsoleIO console) {
        this.userController = userController;
        this.console = console;
    }

    @Override
    public void handle() {
        String dni = console.readRequired("  DNI      : ");
        String fullName = console.readRequired("  Full Name: ");
        String email = console.readRequired("  Email    : ");
        String commune = console.readRequired("  Commune  : ");
        userController.deleteVoter(dni);
        console.println("  Voter deleted successfully!");
    }
}
