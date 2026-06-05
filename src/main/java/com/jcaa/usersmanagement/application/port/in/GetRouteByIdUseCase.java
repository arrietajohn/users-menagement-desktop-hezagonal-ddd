package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetRouteByIdQuery;
import com.jcaa.usersmanagement.domain.model.RouteModel;

public interface GetRouteByIdUseCase {
    RouteModel execute(GetRouteByIdQuery query);
}