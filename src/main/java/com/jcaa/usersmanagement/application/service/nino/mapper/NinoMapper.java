package com.jcaa.usersmanagement.application.service.nino.mapper;

import com.jcaa.usersmanagement.application.service.nino.command.CreateNinoCommand;
import com.jcaa.usersmanagement.application.service.nino.dto.NinoResponse;
import com.jcaa.usersmanagement.domain.model.nino.Nino;

public class NinoMapper {

    public static Nino toEntity(CreateNinoCommand command) {
        return new Nino(
                null,
                command.getMatricula(),
                command.getNombreCompleto(),
                command.getFechaNacimiento(),
                command.getFechaIngreso()
        );
    }

    public static NinoResponse toResponse(Nino nino) {
        return new NinoResponse(nino);
    }

}