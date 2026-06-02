package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.EmpleadoModel;
import com.jcaa.usersmanagement.domain.valueobject.EmpleadoId;

import java.util.Optional;

public interface GetEmpleadoByIdPort {
  Optional<EmpleadoModel> getById(EmpleadoId id);
}
