package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateClienteUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveClientePort;
import com.jcaa.usersmanagement.domain.model.ClienteModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateClienteService implements CreateClienteUseCase {
    private final SaveClientePort saveClientePort;

    @Override
    public ClienteModel execute(String nombre, String apellido, String email, String telefono, String direccion) {
        final ClienteModel cliente = new ClienteModel(null, nombre, apellido, email, telefono, direccion);
        return saveClientePort.save(cliente);
    }
}
