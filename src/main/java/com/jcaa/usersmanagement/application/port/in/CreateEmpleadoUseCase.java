package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateEmpleadoCommand;
import com.jcaa.usersmanagement.domain.model.EmpleadoModel;

public interface CreateEmpleadoUseCase {
  EmpleadoModel execute(CreateEmpleadoCommand command);
}
