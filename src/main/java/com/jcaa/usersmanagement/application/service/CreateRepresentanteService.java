package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateRepresentanteUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveRepresentantePort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateRepresentanteCommand;
import com.jcaa.usersmanagement.application.service.mapper.RepresentanteApplicationMapper;
import com.jcaa.usersmanagement.domain.model.RepresentanteModel;

public class CreateRepresentanteService implements CreateRepresentanteUseCase {

    private final SaveRepresentantePort saveRepresentantePort;
    private final RepresentanteApplicationMapper mapper;

    public CreateRepresentanteService(SaveRepresentantePort saveRepresentantePort) {
        this.saveRepresentantePort = saveRepresentantePort;
        this.mapper = new RepresentanteApplicationMapper();
    }

    @Override
    public RepresentanteModel create(CreateRepresentanteCommand command) {
        RepresentanteModel representante = mapper.toDomain(command);
        return saveRepresentantePort.save(representante);
    }
}