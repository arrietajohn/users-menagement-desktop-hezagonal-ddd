package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.ofertaempleo.io;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleIO {

    private final Scanner scanner;
    private final PrintStream out;

    public ConsoleIO(Scanner scanner, PrintStream out) {
        this.scanner = scanner;
        this.out = out;
    }

    public void println(String msg) {
        out.println(msg);
    }

    public String readLine(String msg) {
        out.println(msg);
        return scanner.nextLine();
    }
}