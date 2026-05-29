package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateResidenciaCommand;
import com.jcaa.usersmanagement.domain.model.ResidenciaModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateResidenciaUseCase {
  ResidenciaModel execute(@NotNull @Valid UpdateResidenciaCommand command);
}
