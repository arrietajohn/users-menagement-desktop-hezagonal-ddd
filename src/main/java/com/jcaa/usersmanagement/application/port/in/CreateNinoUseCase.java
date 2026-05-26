package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.nino.command.CreateNinoCommand;
import com.jcaa.usersmanagement.application.service.nino.dto.NinoResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateNinoUseCase {

    NinoResponse execute(@NotNull @Valid CreateNinoCommand command);

}