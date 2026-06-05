package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateRouteUseCase;
import com.jcaa.usersmanagement.application.port.out.GetRouteByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateRoutePort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateRouteCommand;
import com.jcaa.usersmanagement.application.service.mapper.RouteApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.RouteNotFoundException;
import com.jcaa.usersmanagement.domain.model.RouteModel;
import com.jcaa.usersmanagement.domain.valueobject.RouteId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class UpdateRouteService implements UpdateRouteUseCase {

    private final UpdateRoutePort updateRoutePort;
    private final GetRouteByIdPort getRouteByIdPort;
    private final Validator validator;

    @Override
    public RouteModel execute(final UpdateRouteCommand command) {
        validateCommand(command);

        final RouteId routeId = new RouteId(command.id());

        getRouteByIdPort
                .getById(routeId)
                .orElseThrow(
                        () -> RouteNotFoundException.becauseIdWasNotFound(routeId.value()));

        final RouteModel route =
                RouteApplicationMapper.fromUpdateCommandToModel(command);

        return updateRoutePort.update(route);
    }

    private void validateCommand(final UpdateRouteCommand command) {
        final Set<ConstraintViolation<UpdateRouteCommand>> violations =
                validator.validate(command);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}