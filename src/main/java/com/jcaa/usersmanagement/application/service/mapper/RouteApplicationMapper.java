package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateRouteCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteRouteCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateRouteCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetRouteByIdQuery;
import com.jcaa.usersmanagement.domain.model.RouteModel;
import com.jcaa.usersmanagement.domain.valueobject.RouteId;
import com.jcaa.usersmanagement.domain.valueobject.RouteName;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RouteApplicationMapper {

    public RouteModel fromCreateCommandToModel(final CreateRouteCommand command) {
        return RouteModel.create(
                new RouteId(command.id()),
                new RouteName(command.name()),
                command.neighborhood(),
                command.school(),
                command.journey(),
                command.maxCapacity(),
                command.availableSeats());
    }

    public RouteModel fromUpdateCommandToModel(final UpdateRouteCommand command) {
        return new RouteModel(
                new RouteId(command.id()),
                new RouteName(command.name()),
                command.neighborhood(),
                command.school(),
                command.journey(),
                command.maxCapacity(),
                command.availableSeats(),
                command.active());
    }

    public RouteId fromGetRouteByIdQueryToRouteId(final GetRouteByIdQuery query) {
        return new RouteId(query.id());
    }

    public RouteId fromDeleteCommandToRouteId(final DeleteRouteCommand command) {
        return new RouteId(command.id());
    }
}