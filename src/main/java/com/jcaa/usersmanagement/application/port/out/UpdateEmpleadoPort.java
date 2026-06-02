package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.EmpleadoModel;

public interface UpdateEmpleadoPort {
  EmpleadoModel update(EmpleadoModel empleado);
}
