package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.RepresentanteModel;

public interface GetRepresentanteByIdUseCase {
    RepresentanteModel getById(Integer id);
}