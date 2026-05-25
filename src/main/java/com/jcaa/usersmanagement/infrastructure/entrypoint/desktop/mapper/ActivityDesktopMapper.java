package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.domain.model.Activity;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ActivityResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateActivityRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateActivityRequest;

import java.util.List;

public final class ActivityDesktopMapper {

    private ActivityDesktopMapper() {}

    public static Activity toActivity(final CreateActivityRequest request) {
        return new Activity(
                request.id(),
                request.name(),
                request.description(),
                request.dayOfWeek(),
                request.schedule(),
                request.price(),
                request.isFree(),
                request.employeeId(),
                request.hotelId()
        );
    }

    public static Activity toActivity(final UpdateActivityRequest request) {
        return new Activity(
                request.id(),
                request.name(),
                request.description(),
                request.dayOfWeek(),
                request.schedule(),
                request.price(),
                request.isFree(),
                null,
                0
        );
    }

    public static ActivityResponse toResponse(final Activity activity) {
        return new ActivityResponse(
                activity.getId(),
                activity.getName(),
                activity.getDescription(),
                activity.getDayOfWeek(),
                activity.getSchedule(),
                activity.getPrice(),
                activity.isFree(),
                activity.getEmployeeId(),
                activity.getHotelId()
        );
    }

    public static List<ActivityResponse> toResponseList(final List<Activity> activities) {
        return activities.stream()
                .map(ActivityDesktopMapper::toResponse)
                .toList();
    }
}