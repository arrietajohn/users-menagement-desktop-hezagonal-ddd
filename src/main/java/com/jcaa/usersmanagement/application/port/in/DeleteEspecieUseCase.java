package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DeleteEspecieCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteEspecieUseCase {
    void execute(@NotNull @Valid DeleteEspecieCommand command);
}