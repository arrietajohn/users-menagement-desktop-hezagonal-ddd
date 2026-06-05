package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.RouteModel;

public interface UpdateRoutePort {
    RouteModel update(RouteModel route);
}