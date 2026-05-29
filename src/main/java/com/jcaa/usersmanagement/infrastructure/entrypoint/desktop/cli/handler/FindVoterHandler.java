package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.voter.Voter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;

public class FindVoterHandler implements OperationHandler {

    private final UserController userController;
    private final ConsoleIO console;

    public FindVoterHandler(UserController userController, ConsoleIO console) {
        this.userController = userController;
        this.console = console;
    }

    @Override
    public void handle() {
        String dni = console.readRequired("  DNI      : ");
        String fullName = console.readRequired("  Full Name: ");
        String email = console.readRequired("  Email    : ");
        String commune = console.readRequired("  Commune  : ");
        Voter voter = userController.findVoterByDni(dni);
        console.println("  ----------------------------------------");
        console.println("  ID       : " + voter.getId());
        console.println("  DNI      : " + voter.getDni());
        console.println("  Full Name: " + voter.getFullName());
        console.println("  Email    : " + voter.getEmail());
        console.println("  Commune  : " + voter.getCommune());
        console.println("  ----------------------------------------");
    }
}
