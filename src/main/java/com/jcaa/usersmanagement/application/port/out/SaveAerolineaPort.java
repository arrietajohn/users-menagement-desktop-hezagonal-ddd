package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.AerolineaModel;

public interface SaveAerolineaPort {

    AerolineaModel save(AerolineaModel aerolinea);
}