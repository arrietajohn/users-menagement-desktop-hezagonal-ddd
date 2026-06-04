package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateSubastaCommand;

public interface CrearSubastaUseCase {
    void crear(CreateSubastaCommand command);
}