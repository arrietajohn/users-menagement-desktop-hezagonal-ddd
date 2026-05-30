package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Session;
import com.jcaa.usersmanagement.domain.model.UserModel;

public interface SaveSessionPort {
    Session save(Session Session);
}

