package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.Vehiculomodel;
import java.util.List;

public interface GetVehiculosUseCase {
    List<Vehiculomodel> execute();
}
