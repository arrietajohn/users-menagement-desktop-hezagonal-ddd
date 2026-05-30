package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateEspecieUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveEspeciePort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateEspecieCommand;
import com.jcaa.usersmanagement.application.service.mapper.EspecieApplicationMapper;
import com.jcaa.usersmanagement.domain.model.Especie;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class CreateEspecieService implements CreateEspecieUseCase {

    private final SaveEspeciePort saveEspeciePort;
    private final Validator validator;

    @Override
    public Especie execute(final CreateEspecieCommand command) {
        validateCommand(command);
        final Especie especie = EspecieApplicationMapper.toDomain(command);
        return saveEspeciePort.save(especie);
    }

    private void validateCommand(final CreateEspecieCommand command) {
        final Set<ConstraintViolation<CreateEspecieCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}