package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateProyectoCommand;
import com.jcaa.usersmanagement.domain.model.ProyectoModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateProyectoUseCase {
  ProyectoModel execute(@NotNull @Valid UpdateProyectoCommand command);
}
