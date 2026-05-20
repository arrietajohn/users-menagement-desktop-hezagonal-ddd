package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.Activity;

public interface CreateActivityUseCase {
    void execute(Activity activity);
}