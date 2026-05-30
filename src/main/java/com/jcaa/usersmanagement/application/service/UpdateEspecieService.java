package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateEspecieUseCase;
import com.jcaa.usersmanagement.application.port.out.GetEspecieByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateEspeciePort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateEspecieCommand;
import com.jcaa.usersmanagement.domain.model.Especie;
import com.jcaa.usersmanagement.domain.model.EspecieId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class UpdateEspecieService implements UpdateEspecieUseCase {

    private final UpdateEspeciePort updateEspeciePort;
    private final GetEspecieByIdPort getEspecieByIdPort;
    private final Validator validator;

    @Override
    public Especie execute(final UpdateEspecieCommand command) {
        validateCommand(command);

        final EspecieId id = EspecieId.of(command.id());
        final Especie especie = getEspecieByIdPort.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Especie no encontrada con id: " + command.id()));

        especie.actualizarDescripcion(command.descripcion());
        return updateEspeciePort.update(especie);
    }

    private void validateCommand(final UpdateEspecieCommand command) {
        final Set<ConstraintViolation<UpdateEspecieCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}