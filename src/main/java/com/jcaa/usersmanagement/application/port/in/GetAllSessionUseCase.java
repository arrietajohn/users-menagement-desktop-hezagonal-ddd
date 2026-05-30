package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.Session;

import java.util.List;

public interface GetAllSessionUseCase {
    List<Session> execute();
}
