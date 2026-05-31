package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.ClienteModel;
import java.util.List;

public interface GetAllClientesPort {
    List<ClienteModel> getAll();
}
