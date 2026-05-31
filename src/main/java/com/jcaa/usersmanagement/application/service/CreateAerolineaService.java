package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateAerolineaUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveAerolineaPort;
import com.jcaa.usersmanagement.domain.model.AerolineaModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateAerolineaService implements CreateAerolineaUseCase {

    private final SaveAerolineaPort saveAerolineaPort;

    @Override
    public AerolineaModel execute(AerolineaModel aerolinea) {
        return saveAerolineaPort.save(aerolinea);
    }
}