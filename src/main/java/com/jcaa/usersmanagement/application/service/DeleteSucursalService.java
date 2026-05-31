package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteSucursalUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteSucursalPort;
import com.jcaa.usersmanagement.application.port.out.GetSucursalByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteSucursalCommand;
import com.jcaa.usersmanagement.application.service.mapper.SucursalApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.SucursalNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.SucursaId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class DeleteSucursalService implements DeleteSucursalUseCase {

    private final DeleteSucursalPort deleteSucursalPort;
    private final GetSucursalByIdPort getSucursalByIdPort;
    private final Validator validator;

    @Override
    public void execute(final DeleteSucursalCommand command) {
        validateCommand(command);

        final SucursaId sucursalId = SucursalApplicationMapper.fromDeleteCommandToSucursalId(command);
        ensureSucursalExists(sucursalId);
        deleteSucursalPort.delete(sucursalId);
    }

    private void validateCommand(final DeleteSucursalCommand command) {
        final Set<ConstraintViolation<DeleteSucursalCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void ensureSucursalExists(final SucursaId sucursalId) {
        getSucursalByIdPort
                .getById(sucursalId)
                .orElseThrow(() -> SucursalNotFoundException.becauseIdWasNotFound(sucursalId.value()));
    }
}