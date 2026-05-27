package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DeleteRangoMilitarCommand;

public interface DeleteRangoMilitarUseCase {
    void execute(DeleteRangoMilitarCommand command);
}