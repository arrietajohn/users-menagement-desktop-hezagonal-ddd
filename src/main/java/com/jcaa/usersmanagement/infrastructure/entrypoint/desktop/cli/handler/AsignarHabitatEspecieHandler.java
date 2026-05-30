package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.application.port.in.AsignarHabitatEspecieUseCase;
import com.jcaa.usersmanagement.application.service.dto.command.AsignarHabitatCommand;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AsignarHabitatEspecieHandler implements OperationHandler {

    private final AsignarHabitatEspecieUseCase asignarHabitatUseCase;
    private final ConsoleIO console;

    @Override
    public void handle() {
        console.println("\n=== Asignar Hábitat a Especie ===");

        Integer idEspecie = console.readInt("ID de la Especie: ");
        Integer idHabitat = console.readInt("ID del Hábitat: ");

        try {
            AsignarHabitatCommand command = new AsignarHabitatCommand(idEspecie, idHabitat);
            var especie = asignarHabitatUseCase.execute(command);
            
            console.println("habitat asignado correctamente a la especie.");
            
        } catch (Exception e) {
            console.println("Error: " + e.getMessage());
        }
    }
}