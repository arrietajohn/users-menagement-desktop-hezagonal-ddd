package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.RegistrarExamenMedicoUseCase;
import com.jcaa.usersmanagement.application.service.dto.command.RegistrarExamenMedicoCommand;
import com.jcaa.usersmanagement.domain.model.ExamenMedico;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegistrarExamenMedicoService implements RegistrarExamenMedicoUseCase {

    private final Validator validator;

    @Override
    public ExamenMedico execute(RegistrarExamenMedicoCommand command) {

        System.out.println("Registrando examen médico para especie ID: " + command.idEspecie());
        System.out.println("Diagnóstico: " + command.diagnostico());

        return null; 
    }
}