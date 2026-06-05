package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateRouteUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveRoutePort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateRouteCommand;
import com.jcaa.usersmanagement.application.service.mapper.RouteApplicationMapper;
import com.jcaa.usersmanagement.domain.model.RouteModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class CreateRouteService implements CreateRouteUseCase {

    private final SaveRoutePort saveRoutePort;
    private final Validator validator;

    @Override
    public RouteModel execute(final CreateRouteCommand command) {
        validateCommand(command);

        final RouteModel routeToSave =
                RouteApplicationMapper.fromCreateCommandToModel(command);

        return saveRoutePort.save(routeToSave);
    }

    private void validateCommand(final CreateRouteCommand command) {
        final Set<ConstraintViolation<CreateRouteCommand>> violations =
                validator.validate(command);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}