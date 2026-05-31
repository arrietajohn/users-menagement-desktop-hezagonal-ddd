package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.ClienteModel;

public interface CreateClienteUseCase {
    ClienteModel execute(String nombre, String apellido, String email, String telefono, String direccion);
}
