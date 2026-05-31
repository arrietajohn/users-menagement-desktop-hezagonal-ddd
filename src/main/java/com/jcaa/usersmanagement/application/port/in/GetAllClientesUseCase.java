package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.ClienteModel;
import java.util.List;

public interface GetAllClientesUseCase {
    List<ClienteModel> execute();
}
