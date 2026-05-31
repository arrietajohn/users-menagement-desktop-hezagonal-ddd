package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteAerolineaUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteAerolineaPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteAerolineaService implements DeleteAerolineaUseCase {

    private final DeleteAerolineaPort deleteAerolineaPort;

    @Override
    public void execute(Integer id) {
        deleteAerolineaPort.delete(id);
    }
}