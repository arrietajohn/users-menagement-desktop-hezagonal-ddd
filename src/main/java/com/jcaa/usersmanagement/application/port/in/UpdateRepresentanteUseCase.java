package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateRepresentanteCommand;
import com.jcaa.usersmanagement.domain.model.RepresentanteModel;

public interface UpdateRepresentanteUseCase {
    RepresentanteModel update(UpdateRepresentanteCommand command);
}