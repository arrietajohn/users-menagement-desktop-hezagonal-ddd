package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.Activity;

public interface UpdateDeleteActivityUseCase {
    void executeUpdate(Activity activity);
    void executeDelete(String id);
}