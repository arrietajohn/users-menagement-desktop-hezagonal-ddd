package com.jcaa.usersmanagement.application.service.nino;

import com.jcaa.usersmanagement.application.service.nino.command.CreateNinoCommand;
import com.jcaa.usersmanagement.domain.model.nino.Nino;
import com.jcaa.usersmanagement.domain.model.nino.NinoRepository;

public class CreateNinoUseCase {

    private final NinoRepository ninoRepository;

    public CreateNinoUseCase(NinoRepository ninoRepository) {
        this.ninoRepository = ninoRepository;
    }

    public Nino execute(CreateNinoCommand command) {
        Nino nino = new Nino(
                null,
                command.getMatricula(),
                command.getNombreCompleto(),
                command.getFechaNacimiento(),
                command.getFechaIngreso()
        );

        return ninoRepository.save(nino);
    }
}

