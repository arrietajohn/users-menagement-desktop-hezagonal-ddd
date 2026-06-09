package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Session;

import java.util.List;

public interface GetSessionByChairmanPort {
    List<Session> getByChairman(String chairmanId);
}
