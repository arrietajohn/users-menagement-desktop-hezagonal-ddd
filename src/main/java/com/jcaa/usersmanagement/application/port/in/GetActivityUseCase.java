package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.Activity;
import java.util.List;
import java.util.Optional;

public interface GetActivityUseCase {
    Optional<Activity> executeFindById(String id);
    List<Activity> executeFindAll();
}