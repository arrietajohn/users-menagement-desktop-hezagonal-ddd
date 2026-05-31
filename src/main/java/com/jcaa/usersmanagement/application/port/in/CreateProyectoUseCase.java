package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateProyectoCommand;
import com.jcaa.usersmanagement.domain.model.ProyectoModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateProyectoUseCase {
  ProyectoModel execute(@NotNull @Valid CreateProyectoCommand command);
}
