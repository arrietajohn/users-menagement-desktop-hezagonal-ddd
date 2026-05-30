package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Vehiculomodel;
import java.util.List;

public interface GetAllVehiculosPort {
    List<Vehiculomodel> getAll();
}
