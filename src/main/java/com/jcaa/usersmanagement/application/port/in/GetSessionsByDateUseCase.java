package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.Session;

import java.util.List;

public interface GetSessionsByDateUseCase {
    List<Session> execute(String fecha);
}
