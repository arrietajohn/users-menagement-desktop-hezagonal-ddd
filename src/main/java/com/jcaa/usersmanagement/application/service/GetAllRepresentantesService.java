package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllRepresentantesUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllRepresentantesPort;
import com.jcaa.usersmanagement.domain.model.RepresentanteModel;
import java.util.List;

public class GetAllRepresentantesService implements GetAllRepresentantesUseCase {

    private final GetAllRepresentantesPort getAllRepresentantesPort;

    public GetAllRepresentantesService(GetAllRepresentantesPort getAllRepresentantesPort) {
        this.getAllRepresentantesPort = getAllRepresentantesPort;
    }

    @Override
    public List<RepresentanteModel> getAll() {
        return getAllRepresentantesPort.findAll();
    }
}