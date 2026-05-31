package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DeleteProyectoCommand;

public interface DeleteProyectoUseCase {
  void execute(DeleteProyectoCommand command);
}
