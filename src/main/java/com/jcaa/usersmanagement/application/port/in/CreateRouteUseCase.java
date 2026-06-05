package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateRouteCommand;
import com.jcaa.usersmanagement.domain.model.RouteModel;

public interface CreateRouteUseCase {
    RouteModel execute(CreateRouteCommand command);
}