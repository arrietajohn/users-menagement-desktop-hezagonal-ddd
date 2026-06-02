package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.EmpleadoModel;

import java.util.Optional;

public interface GetEmpleadoByEmailPort {
  Optional<EmpleadoModel> getByEmail(String email);
}
