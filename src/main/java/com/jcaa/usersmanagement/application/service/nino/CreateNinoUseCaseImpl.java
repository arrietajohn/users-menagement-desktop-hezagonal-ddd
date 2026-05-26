package com.jcaa.usersmanagement.application.service.nino;

import com.jcaa.usersmanagement.application.port.in.CreateNinoUseCase;
import com.jcaa.usersmanagement.application.service.nino.command.CreateNinoCommand;
import com.jcaa.usersmanagement.application.service.nino.dto.NinoResponse;
import com.jcaa.usersmanagement.domain.model.nino.Nino;
import com.jcaa.usersmanagement.domain.model.nino.NinoRepository;

public class CreateNinoUseCaseImpl implements CreateNinoUseCase {

    private final NinoRepository ninoRepository;

    public CreateNinoUseCaseImpl(NinoRepository ninoRepository) {
        this.ninoRepository = ninoRepository;
    }

    @Override
    public NinoResponse execute(CreateNinoCommand command) {
        // Verificar si ya existe
        if (ninoRepository.existsByMatricula(command.getMatricula())) {
            throw new IllegalArgumentException("Ya existe un niño con la matrícula: " + command.getMatricula().getValue());
        }

        // Crear entidad
        Nino nino = new Nino(
                null,
                command.getMatricula(),
                command.getNombreCompleto(),
                command.getFechaNacimiento(),
                command.getFechaIngreso()
        );

        Nino savedNino = ninoRepository.save(nino);
        return new NinoResponse(savedNino);
    }
}