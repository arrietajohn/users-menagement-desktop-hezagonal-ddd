package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteRepresentanteUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteRepresentantePort;
import com.jcaa.usersmanagement.application.port.out.GetRepresentanteByIdPort;
import com.jcaa.usersmanagement.domain.exception.RepresentanteNotFoundException;

public class DeleteRepresentanteService implements DeleteRepresentanteUseCase {

    private final DeleteRepresentantePort deleteRepresentantePort;
    private final GetRepresentanteByIdPort getRepresentanteByIdPort;

    public DeleteRepresentanteService(DeleteRepresentantePort deleteRepresentantePort,
                                      GetRepresentanteByIdPort getRepresentanteByIdPort) {
        this.deleteRepresentantePort = deleteRepresentantePort;
        this.getRepresentanteByIdPort = getRepresentanteByIdPort;
    }

    @Override
    public void delete(Integer id) {
        getRepresentanteByIdPort.findById(id)
                .orElseThrow(() -> new RepresentanteNotFoundException(String.valueOf(id)));
        deleteRepresentantePort.deleteById(id);
    }
}