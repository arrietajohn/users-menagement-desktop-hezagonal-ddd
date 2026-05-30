package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetEspecieByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetEspecieByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetEspecieByIdQuery;
import com.jcaa.usersmanagement.domain.model.Especie;
import com.jcaa.usersmanagement.domain.model.EspecieId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class GetEspecieByIdService implements GetEspecieByIdUseCase {

    private final GetEspecieByIdPort getEspecieByIdPort;
    private final Validator validator;

    @Override
    public Especie execute(final GetEspecieByIdQuery query) {
        validateQuery(query);
        final EspecieId id = EspecieId.of(query.id());
        return getEspecieByIdPort.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Especie no encontrada con id: " + query.id()));
    }

    private void validateQuery(final GetEspecieByIdQuery query) {
        final Set<ConstraintViolation<GetEspecieByIdQuery>> violations = validator.validate(query);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}