package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteEspecieUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteEspeciePort;
import com.jcaa.usersmanagement.application.port.out.GetEspecieByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteEspecieCommand;
import com.jcaa.usersmanagement.domain.model.EspecieId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class DeleteEspecieService implements DeleteEspecieUseCase {

    private final DeleteEspeciePort deleteEspeciePort;
    private final GetEspecieByIdPort getEspecieByIdPort;
    private final Validator validator;

    @Override
    public void execute(final DeleteEspecieCommand command) {
        validateCommand(command);
 
        final EspecieId id = EspecieId.of(command.id());
        getEspecieByIdPort.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Especie no encontrada con id: " + command.id()));

        deleteEspeciePort.delete(id);
    }

    private void validateCommand(final DeleteEspecieCommand command) {
        final Set<ConstraintViolation<DeleteEspecieCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}