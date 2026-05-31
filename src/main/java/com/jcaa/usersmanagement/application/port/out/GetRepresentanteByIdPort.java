package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.RepresentanteModel;
import java.util.Optional;

public interface GetRepresentanteByIdPort {
    Optional<RepresentanteModel> findById(Integer id);
}