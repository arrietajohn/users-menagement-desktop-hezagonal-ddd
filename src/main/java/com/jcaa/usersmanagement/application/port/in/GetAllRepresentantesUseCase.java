package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.RepresentanteModel;
import java.util.List;

public interface GetAllRepresentantesUseCase {
    List<RepresentanteModel> getAll();
}