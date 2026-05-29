// application/service/GetAllResidenciasService.java
package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllResidenciasUseCase;
import com.jcaa.usersmanagement.application.port.out.ResidenciaRepositoryPort;
import com.jcaa.usersmanagement.domain.model.ResidenciaModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class GetAllResidenciasService implements GetAllResidenciasUseCase {

    private final ResidenciaRepositoryPort residenciaRepositoryPort;

    @Override
    public List<ResidenciaModel> execute() {
        return residenciaRepositoryPort.findAll();
    }
}