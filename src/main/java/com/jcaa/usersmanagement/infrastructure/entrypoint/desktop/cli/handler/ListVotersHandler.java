package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.voter.Voter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;
import java.util.List;

public class ListVotersHandler implements OperationHandler {

    private final UserController userController;
    private final ConsoleIO console;

    public ListVotersHandler(UserController userController, ConsoleIO console) {
        this.userController = userController;
        this.console = console;
    }

    @Override
    public void handle() {
        List<Voter> voters = userController.listVoters();
        if (voters.isEmpty()) {
            console.println("  No voters registered.");
            return;
        }
        console.println("  ----------------------------------------");
        for (Voter voter : voters) {
            console.println("  ID       : " + voter.getId());
            console.println("  DNI      : " + voter.getDni());
            console.println("  Full Name: " + voter.getFullName());
            console.println("  Email    : " + voter.getEmail());
            console.println("  Commune  : " + voter.getCommune());
            console.println("  ----------------------------------------");
        }
    }
}
