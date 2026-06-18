package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetSessionByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.GetUserByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetSessionByIdPort;
import com.jcaa.usersmanagement.application.port.out.GetUserByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetSessionByIdQuery;
import com.jcaa.usersmanagement.application.service.dto.query.GetUserByIdQuery;
import com.jcaa.usersmanagement.application.service.mapper.SessionApplicationMapper;
import com.jcaa.usersmanagement.application.service.mapper.UserApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.InvalidSessionIdException;
import com.jcaa.usersmanagement.domain.exception.UserNotFoundException;
import com.jcaa.usersmanagement.domain.model.Session;
import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.domain.valueobject.SessionId;
import com.jcaa.usersmanagement.domain.valueobject.UserId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class GetSessionByIdService implements GetSessionByIdUseCase {

    private final GetSessionByIdPort getSessionByIdPort;
    private final Validator validator;

    @Override
    public Session execute(final GetSessionByIdQuery query) {
        validateQuery(query);

        final SessionId sessionId = SessionApplicationMapper.fromGetSessionByIdQueryToSessionId(query);
        return getSessionByIdPort
                .getById(sessionId)
                .orElseThrow(() -> InvalidSessionIdException.becauseIdWasNotFound(query.id()));
    }

    private void validateQuery(final GetSessionByIdQuery query) {
        final Set<ConstraintViolation<GetSessionByIdQuery>> violations = validator.validate(query);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}