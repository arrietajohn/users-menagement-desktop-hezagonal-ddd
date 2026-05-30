package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteVehiculoPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteVehiculoService implements DeleteVehiculoUseCase {

    private final DeleteVehiculoPort deleteVehiculoPort;

    @Override
    public void execute(Integer idBastidor) {
        deleteVehiculoPort.delete(idBastidor);
    }
}
