package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateSessionUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveSessionPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateSessionCommand;
import com.jcaa.usersmanagement.application.service.mapper.SessionApplicationMapper;
import com.jcaa.usersmanagement.domain.model.Session;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.Set;

@Log
@RequiredArgsConstructor
public final class CreateSessionService implements CreateSessionUseCase {

    private final SaveSessionPort saveSessionPort;
    private final Validator validator;


    @Override
    public Session execute(final CreateSessionCommand command) {
        validateCommand(command);

        final Session SessionToSave = SessionApplicationMapper.fromCreateCommandToModel(command);
        final Session savedSession = saveSessionPort.save(SessionToSave);

        return savedSession;
    }

    private void validateCommand(final CreateSessionCommand command) {
        final Set<ConstraintViolation<CreateSessionCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

    }
}