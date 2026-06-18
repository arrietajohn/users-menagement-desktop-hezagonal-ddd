package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Session;
import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.domain.valueobject.SessionId;
import com.jcaa.usersmanagement.domain.valueobject.UserId;

import java.util.Optional;
public interface GetSessionByIdPort {
    Optional<Session> getById(SessionId sessionId);
}
