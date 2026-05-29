// application/service/DeleteResidenciaService.java
package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteResidenciaUseCase;
import com.jcaa.usersmanagement.application.port.out.ResidenciaRepositoryPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteResidenciaCommand;
import com.jcaa.usersmanagement.application.service.mapper.ResidenciaApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.ResidenciaNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.ResidenciaId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class DeleteResidenciaService implements DeleteResidenciaUseCase {

    private final ResidenciaRepositoryPort residenciaRepositoryPort;
    private final Validator validator;

    @Override
    public void execute(final DeleteResidenciaCommand command) {
        validateCommand(command);
        final ResidenciaId id = ResidenciaApplicationMapper.fromDeleteCommandToResidenciaId(command);
        residenciaRepositoryPort.findById(id)
                .orElseThrow(() -> ResidenciaNotFoundException.becauseIdWasNotFound(command.id()));
        residenciaRepositoryPort.delete(id);
    }

    private void validateCommand(final DeleteResidenciaCommand command) {
        final Set<ConstraintViolation<DeleteResidenciaCommand>> violations =
                validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}