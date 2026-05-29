// application/service/CreateResidenciaService.java
package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateResidenciaUseCase;
import com.jcaa.usersmanagement.application.port.out.ResidenciaRepositoryPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateResidenciaCommand;
import com.jcaa.usersmanagement.application.service.mapper.ResidenciaApplicationMapper;
import com.jcaa.usersmanagement.domain.model.ResidenciaModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class CreateResidenciaService implements CreateResidenciaUseCase {

    private final ResidenciaRepositoryPort residenciaRepositoryPort;
    private final Validator validator;

    @Override
    public ResidenciaModel execute(final CreateResidenciaCommand command) {
        validateCommand(command);
        final ResidenciaModel model = ResidenciaApplicationMapper.fromCreateCommandToModel(command);
        return residenciaRepositoryPort.save(model);
    }

    private void validateCommand(final CreateResidenciaCommand command) {
        final Set<ConstraintViolation<CreateResidenciaCommand>> violations =
                validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}