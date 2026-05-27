package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateRangoMilitarCommand;
import com.jcaa.usersmanagement.domain.model.RangoMilitarModel;

public interface CreateRangoMilitarUseCase {
    RangoMilitarModel execute(CreateRangoMilitarCommand command);
}