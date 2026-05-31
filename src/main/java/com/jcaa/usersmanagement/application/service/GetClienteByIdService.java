package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetClienteByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetClienteByIdPort;
import com.jcaa.usersmanagement.domain.model.ClienteModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetClienteByIdService implements GetClienteByIdUseCase {
    private final GetClienteByIdPort getClienteByIdPort;

    @Override
    public ClienteModel execute(Integer idCliente) {
        return getClienteByIdPort.getById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + idCliente));
    }
}
