package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;
import java.util.Scanner;

public class ProyectoViewCLI {
    private final DependencyContainer container;
    private final Scanner scanner;

    public ProyectoViewCLI(DependencyContainer container) {
        this.container = container;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("\n--- MÓDULO DE PROYECTOS ---");
        
    }
}