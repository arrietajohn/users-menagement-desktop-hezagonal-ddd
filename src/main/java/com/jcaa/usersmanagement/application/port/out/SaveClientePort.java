package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.ClienteModel;

public interface SaveClientePort {
    ClienteModel save(ClienteModel cliente);
}
