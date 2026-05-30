package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.RegistrarExamenMedicoCommand;
import com.jcaa.usersmanagement.domain.model.ExamenMedico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface RegistrarExamenMedicoUseCase {

    ExamenMedico execute(@NotNull @Valid RegistrarExamenMedicoCommand command);
}
