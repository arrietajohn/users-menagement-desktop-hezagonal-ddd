package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetRepresentanteByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetRepresentanteByIdPort;
import com.jcaa.usersmanagement.domain.exception.RepresentanteNotFoundException;
import com.jcaa.usersmanagement.domain.model.RepresentanteModel;

public class GetRepresentanteByIdService implements GetRepresentanteByIdUseCase {

    private final GetRepresentanteByIdPort getRepresentanteByIdPort;

    public GetRepresentanteByIdService(GetRepresentanteByIdPort getRepresentanteByIdPort) {
        this.getRepresentanteByIdPort = getRepresentanteByIdPort;
    }

    @Override
    public RepresentanteModel getById(Integer id) {
        return getRepresentanteByIdPort.findById(id)
                .orElseThrow(() -> new RepresentanteNotFoundException(String.valueOf(id)));
    }
}