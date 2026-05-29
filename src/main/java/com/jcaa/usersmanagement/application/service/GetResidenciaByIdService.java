// application/service/GetResidenciaByIdService.java
package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetResidenciaByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.ResidenciaRepositoryPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetResidenciaByIdQuery;
import com.jcaa.usersmanagement.application.service.mapper.ResidenciaApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.ResidenciaNotFoundException;
import com.jcaa.usersmanagement.domain.model.ResidenciaModel;
import com.jcaa.usersmanagement.domain.valueobject.ResidenciaId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class GetResidenciaByIdService implements GetResidenciaByIdUseCase {

    private final ResidenciaRepositoryPort residenciaRepositoryPort;
    private final Validator validator;

    @Override
    public ResidenciaModel execute(final GetResidenciaByIdQuery query) {
        validateQuery(query);
        final ResidenciaId id =
                ResidenciaApplicationMapper.fromGetResidenciaByIdQueryToResidenciaId(query);
        return residenciaRepositoryPort.findById(id)
                .orElseThrow(() -> ResidenciaNotFoundException.becauseIdWasNotFound(query.id()));
    }

    private void validateQuery(final GetResidenciaByIdQuery query) {
        final Set<ConstraintViolation<GetResidenciaByIdQuery>> violations =
                validator.validate(query);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}