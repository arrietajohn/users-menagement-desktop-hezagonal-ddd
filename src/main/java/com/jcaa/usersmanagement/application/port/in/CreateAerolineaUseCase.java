package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.AerolineaModel;

public interface CreateAerolineaUseCase {

    AerolineaModel execute(AerolineaModel aerolinea);
}