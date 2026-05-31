package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.ClienteModel;
import java.util.Optional;

public interface GetClienteByIdPort {
    Optional<ClienteModel> getById(Integer idCliente);
}
