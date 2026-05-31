package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateClienteUseCase;
import com.jcaa.usersmanagement.application.port.out.UpdateClientePort;
import com.jcaa.usersmanagement.domain.model.ClienteModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateClienteService implements UpdateClienteUseCase {
    private final UpdateClientePort updateClientePort;

    @Override
    public ClienteModel execute(Integer idCliente, String nombre, String apellido, String email, String telefono, String direccion) {
        final ClienteModel cliente = new ClienteModel(idCliente, nombre, apellido, email, telefono, direccion);
        return updateClientePort.update(cliente);
    }
}
