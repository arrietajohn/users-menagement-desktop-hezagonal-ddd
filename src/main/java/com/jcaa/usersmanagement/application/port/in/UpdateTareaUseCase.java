package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateTareaCommand;
import com.jcaa.usersmanagement.domain.model.TareaModel;

public interface UpdateTareaUseCase {
  TareaModel execute(UpdateTareaCommand command);
}
