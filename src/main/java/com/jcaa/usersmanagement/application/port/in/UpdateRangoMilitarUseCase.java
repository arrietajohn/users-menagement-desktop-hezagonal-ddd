package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateRangoMilitarCommand;
import com.jcaa.usersmanagement.domain.model.RangoMilitarModel;

public interface UpdateRangoMilitarUseCase {
    RangoMilitarModel execute(UpdateRangoMilitarCommand command);
}