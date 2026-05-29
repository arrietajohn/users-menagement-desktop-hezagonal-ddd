package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DeleteResidenciaCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteResidenciaUseCase {
  void execute(@NotNull @Valid DeleteResidenciaCommand command);
}
