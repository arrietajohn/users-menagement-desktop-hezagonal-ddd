package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteRouteUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteRoutePort;
import com.jcaa.usersmanagement.application.port.out.GetRouteByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteRouteCommand;
import com.jcaa.usersmanagement.application.service.mapper.RouteApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.RouteNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.RouteId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class DeleteRouteService implements DeleteRouteUseCase {

    private final DeleteRoutePort deleteRoutePort;
    private final GetRouteByIdPort getRouteByIdPort;
    private final Validator validator;

    @Override
    public void execute(final DeleteRouteCommand command) {
        validateCommand(command);

        final RouteId routeId =
                RouteApplicationMapper.fromDeleteCommandToRouteId(command);

        getRouteByIdPort
                .getById(routeId)
                .orElseThrow(
                        () -> RouteNotFoundException.becauseIdWasNotFound(routeId.value()));

        deleteRoutePort.delete(routeId);
    }

    private void validateCommand(final DeleteRouteCommand command) {
        final Set<ConstraintViolation<DeleteRouteCommand>> violations =
                validator.validate(command);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}