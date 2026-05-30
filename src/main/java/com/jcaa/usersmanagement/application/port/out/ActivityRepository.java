package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Activity;
import java.util.List;
import java.util.Optional;

public interface ActivityRepository {
    void save(Activity activity);
    Optional<Activity> findById(String id);
    void update(Activity activity);
    void deleteById(String id);
    List<Activity> findAll();
}