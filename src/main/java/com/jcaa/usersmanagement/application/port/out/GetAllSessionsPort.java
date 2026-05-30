package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Session;
import com.jcaa.usersmanagement.domain.model.UserModel;

import java.util.List;

public interface GetAllSessionsPort {

    List<Session> getAll();
}
