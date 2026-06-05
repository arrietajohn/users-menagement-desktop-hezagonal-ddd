package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.RouteModel;
import com.jcaa.usersmanagement.domain.valueobject.RouteId;

import java.util.Optional;

public interface GetRouteByIdPort {
    Optional<RouteModel> getById(RouteId routeId);
}