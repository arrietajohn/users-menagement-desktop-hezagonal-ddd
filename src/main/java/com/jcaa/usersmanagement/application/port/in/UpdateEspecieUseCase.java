package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateEspecieCommand;
import com.jcaa.usersmanagement.domain.model.Especie;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateEspecieUseCase {
    Especie execute(@NotNull @Valid UpdateEspecieCommand command);
}