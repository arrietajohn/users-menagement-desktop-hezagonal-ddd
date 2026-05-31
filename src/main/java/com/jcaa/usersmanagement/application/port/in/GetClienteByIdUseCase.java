package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.ClienteModel;

public interface GetClienteByIdUseCase {
    ClienteModel execute(Integer idCliente);
}
