package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DeleteTareaCommand;

public interface DeleteTareaUseCase {
  void execute(DeleteTareaCommand command);
}
