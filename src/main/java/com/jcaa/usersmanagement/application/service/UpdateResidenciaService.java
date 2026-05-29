// application/service/UpdateResidenciaService.java
package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateResidenciaUseCase;
import com.jcaa.usersmanagement.application.port.out.ResidenciaRepositoryPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateResidenciaCommand;
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
public final class UpdateResidenciaService implements UpdateResidenciaUseCase {

    private final ResidenciaRepositoryPort residenciaRepositoryPort;
    private final Validator validator;

    @Override
    public ResidenciaModel execute(final UpdateResidenciaCommand command) {
        validateCommand(command);
        final ResidenciaId id = new ResidenciaId(command.id());
        residenciaRepositoryPort.findById(id)
                .orElseThrow(() -> ResidenciaNotFoundException.becauseIdWasNotFound(command.id()));
        final ResidenciaModel model = ResidenciaApplicationMapper.fromUpdateCommandToModel(command);
        return residenciaRepositoryPort.update(model);
    }

    private void validateCommand(final UpdateResidenciaCommand command) {
        final Set<ConstraintViolation<UpdateResidenciaCommand>> violations =
                validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}