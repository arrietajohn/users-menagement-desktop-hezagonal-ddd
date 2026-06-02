package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.valueobject.EmpleadoId;

public interface DeleteEmpleadoPort {
  void delete(EmpleadoId id);
}
