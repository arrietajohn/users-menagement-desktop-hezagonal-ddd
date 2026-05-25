package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateActivityUseCase;
import com.jcaa.usersmanagement.application.port.in.GetActivityUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateDeleteActivityUseCase;
import com.jcaa.usersmanagement.domain.model.Activity;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ActivityResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateActivityRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateActivityRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.ActivityDesktopMapper;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public final class ActivityController {

    private final CreateActivityUseCase createActivityUseCase;
    private final GetActivityUseCase getActivityUseCase;
    private final UpdateDeleteActivityUseCase updateDeleteActivityUseCase;

    public List<ActivityResponse> listAllActivities() {
        final var activities = getActivityUseCase.executeFindAll();
        return ActivityDesktopMapper.toResponseList(activities);
    }

    public Optional<ActivityResponse> findActivityById(final String id) {
        return getActivityUseCase.executeFindById(id)
                .map(ActivityDesktopMapper::toResponse);
    }

    public void createActivity(final CreateActivityRequest request) {
        final Activity activity = ActivityDesktopMapper.toActivity(request);
        createActivityUseCase.execute(activity);
    }

    public void updateActivity(final UpdateActivityRequest request) {
        final Activity activity = ActivityDesktopMapper.toActivity(request);
        updateDeleteActivityUseCase.executeUpdate(activity);
    }

    public void deleteActivity(final String id) {
        updateDeleteActivityUseCase.executeDelete(id);
    }
}
