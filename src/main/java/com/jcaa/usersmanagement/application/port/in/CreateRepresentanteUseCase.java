package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateRepresentanteCommand;
import com.jcaa.usersmanagement.domain.model.RepresentanteModel;

public interface CreateRepresentanteUseCase {
    RepresentanteModel create(CreateRepresentanteCommand command);
}
