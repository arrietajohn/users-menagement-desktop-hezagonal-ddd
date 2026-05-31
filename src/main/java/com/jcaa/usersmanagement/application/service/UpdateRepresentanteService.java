package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateRepresentanteUseCase;
import com.jcaa.usersmanagement.application.port.out.GetRepresentanteByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateRepresentantePort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateRepresentanteCommand;
import com.jcaa.usersmanagement.application.service.mapper.RepresentanteApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.RepresentanteNotFoundException;
import com.jcaa.usersmanagement.domain.model.RepresentanteModel;

public class UpdateRepresentanteService implements UpdateRepresentanteUseCase {

    private final GetRepresentanteByIdPort getRepresentanteByIdPort;
    private final UpdateRepresentantePort updateRepresentantePort;
    private final RepresentanteApplicationMapper mapper;

    public UpdateRepresentanteService(GetRepresentanteByIdPort getRepresentanteByIdPort,
                                      UpdateRepresentantePort updateRepresentantePort) {
        this.getRepresentanteByIdPort = getRepresentanteByIdPort;
        this.updateRepresentantePort = updateRepresentantePort;
        this.mapper = new RepresentanteApplicationMapper();
    }

    @Override
    public RepresentanteModel update(UpdateRepresentanteCommand command) {
        RepresentanteModel representante = getRepresentanteByIdPort.findById(command.id())
                .orElseThrow(() -> new RepresentanteNotFoundException(String.valueOf(command.id())));
        mapper.updateDomain(representante, command);
        return updateRepresentantePort.update(representante);
    }
}