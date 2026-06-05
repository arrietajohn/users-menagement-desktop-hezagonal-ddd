package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.valueobject.RouteId;

public interface DeleteRoutePort {
    void delete(RouteId routeId);
}