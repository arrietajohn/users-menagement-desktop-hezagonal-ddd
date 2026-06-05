package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DeleteRouteCommand;

public interface DeleteRouteUseCase {
    void execute(DeleteRouteCommand command);
}