package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.valueobject.RouteId;
import com.jcaa.usersmanagement.domain.valueobject.RouteName;
import lombok.Value;

@Value
public class RouteModel {

    RouteId id;
    RouteName name;

    String neighborhood;
    String school;
    String journey;

    Integer maxCapacity;
    Integer availableSeats;

    Boolean active;

    public static RouteModel create(
            final RouteId id,
            final RouteName name,
            final String neighborhood,
            final String school,
            final String journey,
            final Integer maxCapacity,
            final Integer availableSeats) {

        return new RouteModel(
                id,
                name,
                neighborhood,
                school,
                journey,
                maxCapacity,
                availableSeats,
                true
        );
    }

    public RouteModel deactivate() {
        return new RouteModel(
                id,
                name,
                neighborhood,
                school,
                journey,
                maxCapacity,
                availableSeats,
                false
        );
    }
}