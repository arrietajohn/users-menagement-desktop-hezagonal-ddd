package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateOfertaCommand;

public interface RegistrarOfertaUseCase {
    void registrar(CreateOfertaCommand command);
}