package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.RepresentanteModel;
import java.util.List;

public interface GetAllRepresentantesPort {
    List<RepresentanteModel> findAll();
}