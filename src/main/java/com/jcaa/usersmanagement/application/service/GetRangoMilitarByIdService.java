package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetRangoMilitarByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetRangoMilitarByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetRangoMilitarByIdQuery;
import com.jcaa.usersmanagement.domain.exception.RangoMilitarNotFoundException;
import com.jcaa.usersmanagement.domain.model.RangoMilitarModel;
import com.jcaa.usersmanagement.domain.valueobject.RangoId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import java.util.Set;

@RequiredArgsConstructor
public final class GetRangoMilitarByIdService implements GetRangoMilitarByIdUseCase {

    private final GetRangoMilitarByIdPort getRangoMilitarByIdPort;
    private final Validator validator;

    @Override
    public RangoMilitarModel execute(final GetRangoMilitarByIdQuery query) {
        validateQuery(query);
        final RangoId id = new RangoId(query.id());
        return getRangoMilitarByIdPort
                .getById(id)
                .orElseThrow(() -> RangoMilitarNotFoundException.becauseIdWasNotFound(id.value()));
    }

    private void validateQuery(final GetRangoMilitarByIdQuery query) {
        final Set<ConstraintViolation<GetRangoMilitarByIdQuery>> violations =
                validator.validate(query);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
