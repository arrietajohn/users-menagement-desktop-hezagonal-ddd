package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateEmpleadoCommand;
import com.jcaa.usersmanagement.domain.model.EmpleadoModel;

public interface UpdateEmpleadoUseCase {
  EmpleadoModel execute(UpdateEmpleadoCommand command);
}
