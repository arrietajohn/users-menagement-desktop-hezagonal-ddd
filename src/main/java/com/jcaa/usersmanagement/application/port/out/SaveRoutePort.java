package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.RouteModel;

public interface SaveRoutePort {
    RouteModel save(RouteModel route);
}