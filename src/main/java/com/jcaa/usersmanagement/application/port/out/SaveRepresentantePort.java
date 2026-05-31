package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.RepresentanteModel;

public interface SaveRepresentantePort {
    RepresentanteModel save(RepresentanteModel representante);
}