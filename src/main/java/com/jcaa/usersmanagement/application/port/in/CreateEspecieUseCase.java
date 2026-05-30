package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateEspecieCommand;
import com.jcaa.usersmanagement.domain.model.Especie;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateEspecieUseCase {
    Especie execute(@NotNull @Valid CreateEspecieCommand command);
}