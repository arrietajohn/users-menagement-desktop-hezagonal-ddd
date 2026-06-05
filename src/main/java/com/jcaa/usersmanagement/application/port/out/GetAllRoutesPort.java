package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.RouteModel;

import java.util.List;

public interface GetAllRoutesPort {
    List<RouteModel> getAll();
}