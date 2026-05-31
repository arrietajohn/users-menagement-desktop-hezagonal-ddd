package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteClienteUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteClientePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteClienteService implements DeleteClienteUseCase {
    private final DeleteClientePort deleteClientePort;

    @Override
    public void execute(Integer idCliente) {
        deleteClientePort.delete(idCliente);
    }
}
