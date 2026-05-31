package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.AerolineaModel;
import java.util.Optional;

public interface GetAerolineaByIdPort {

    Optional<AerolineaModel> getById(Integer idAerolinea);
}