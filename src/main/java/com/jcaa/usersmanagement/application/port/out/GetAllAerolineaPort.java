package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.AerolineaModel;
import java.util.List;

public interface GetAllAerolineaPort {

    List<AerolineaModel> getAll();
}