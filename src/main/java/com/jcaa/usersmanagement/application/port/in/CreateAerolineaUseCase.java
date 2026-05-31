package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateAerolineaCommand;
import com.jcaa.usersmanagement.domain.model.AerolineaModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateAerolineaUseCase {

    AerolineaModel execute(@NotNull @Valid CreateAerolineaCommand command);
}