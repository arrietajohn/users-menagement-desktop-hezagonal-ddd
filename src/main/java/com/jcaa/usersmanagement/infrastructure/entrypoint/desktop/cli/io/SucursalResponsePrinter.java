package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.SucursalResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class SucursalResponsePrinter {

    private static final String SEPARATOR = "-".repeat(52);
    private static final String ROW_FORMAT = "  %-15s : %s%n";

    private final ConsoleIO console;

    public void print(final SucursalResponse response) {
        console.println(SEPARATOR);
        console.printf(ROW_FORMAT, "ID",           response.id());
        console.printf(ROW_FORMAT, "Numero",       response.numero());
        console.printf(ROW_FORMAT, "Direccion",    response.direccion());
        console.printf(ROW_FORMAT, "Codigo Postal",response.codigoPostal());
        console.printf(ROW_FORMAT, "Ciudad",       response.ciudad());
        console.printf(ROW_FORMAT, "Banco ID",     response.bancoId());
        console.println(SEPARATOR);
    }
}