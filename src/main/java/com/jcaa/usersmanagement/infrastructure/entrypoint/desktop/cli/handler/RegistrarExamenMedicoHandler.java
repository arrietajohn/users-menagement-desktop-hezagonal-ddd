package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.EspecieController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegistrarExamenMedicoHandler implements OperationHandler {

    private final EspecieController especieController;
    private final ConsoleIO console;

    @Override
    public void handle() {
        console.println("\n=== Registrar Examen Médico ===");

        String idEspecie = console.readRequired("ID de la Especie: ");
        Integer idCuidador = console.readInt("ID del Cuidador: ");
        String diagnostico = console.readRequired("Diagnóstico: ");
        String tratamiento = console.readOptional("Tratamiento (opcional): ");
        String observaciones = console.readOptional("Observaciones (opcional): ");

        especieController.registrarExamenMedico(
            idEspecie, 
            idCuidador, 
            diagnostico, 
            tratamiento, 
            observaciones
        );

        console.println("✅ Examen médico registrado correctamente.");
    }
}