package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetRouteByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetRouteByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetRouteByIdQuery;
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
public final class GetRouteByIdService implements GetRouteByIdUseCase {

    private final GetRouteByIdPort getRouteByIdPort;
    private final Validator validator;

    @Override
    public RouteModel execute(final GetRouteByIdQuery query) {
        validateQuery(query);

        final RouteId routeId =
                RouteApplicationMapper.fromGetRouteByIdQueryToRouteId(query);

        return getRouteByIdPort
                .getById(routeId)
                .orElseThrow(
                        () -> RouteNotFoundException.becauseIdWasNotFound(routeId.value()));
    }

    private void validateQuery(final GetRouteByIdQuery query) {
        final Set<ConstraintViolation<GetRouteByIdQuery>> violations =
                validator.validate(query);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}