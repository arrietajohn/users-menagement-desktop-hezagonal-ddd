package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateTareaCommand;
import com.jcaa.usersmanagement.domain.model.TareaModel;

public interface CreateTareaUseCase {
  TareaModel execute(CreateTareaCommand command);
}
