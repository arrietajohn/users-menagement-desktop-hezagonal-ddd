package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.RouteModel;
import java.util.List;

public interface GetAllRoutesUseCase {
    List<RouteModel> execute();
}