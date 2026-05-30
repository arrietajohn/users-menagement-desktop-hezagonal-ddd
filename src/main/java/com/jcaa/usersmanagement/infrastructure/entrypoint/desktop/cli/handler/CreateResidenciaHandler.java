package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.ResidenciaNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ResidenciaResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ResidenciaController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateResidenciaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ResidenciaResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateResidenciaHandler implements OperationHandler {

    private final ResidenciaController residenciaController;
    private final ConsoleIO console;
    private final ResidenciaResponsePrinter printer;

    @Override
    public void handle() {
        final Integer personaId   = console.readInt("Persona ID              : ");
        final String municipioId  = console.readRequired("Municipio ID            : ");
        final String pais         = console.readOptional("Pais extranjero         : ");
        final String direccion    = console.readOptional("Direccion               : ");
        final String fechaInicio  = console.readRequired("Fecha inicio (YYYY-MM-DD): ");

        try {
            final ResidenciaResponse created = residenciaController.createResidencia(
                    new CreateResidenciaRequest(
                            personaId,
                            municipioId,
                            pais.isBlank() ? null : pais,
                            direccion.isBlank() ? null : direccion,
                            fechaInicio));
            console.println("\n  Residencia creada exitosamente.");
            printer.print(created);
        } catch (final ResidenciaNotFoundException exception) {
            console.println("  Error: " + exception.getMessage());
        }
    }
}