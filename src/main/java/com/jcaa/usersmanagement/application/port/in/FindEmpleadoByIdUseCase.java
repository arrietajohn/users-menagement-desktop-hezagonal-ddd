package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.FindEmpleadoByIdQuery;
import com.jcaa.usersmanagement.domain.model.EmpleadoModel;

public interface FindEmpleadoByIdUseCase {
  EmpleadoModel execute(FindEmpleadoByIdQuery query);
}
