package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllEspeciesUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllEspeciesPort;
import com.jcaa.usersmanagement.domain.model.Especie;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class GetAllEspeciesService implements GetAllEspeciesUseCase {

    private final GetAllEspeciesPort getAllEspeciesPort;

    @Override
    public List<Especie> execute() {
        return getAllEspeciesPort.getAll();
    }
}