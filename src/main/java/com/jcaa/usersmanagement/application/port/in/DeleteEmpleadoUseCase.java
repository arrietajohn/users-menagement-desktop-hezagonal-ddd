package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DeleteEmpleadoCommand;

public interface DeleteEmpleadoUseCase {
  void execute(DeleteEmpleadoCommand command);
}
