package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateSucursalUseCase;
import com.jcaa.usersmanagement.application.port.out.GetSucursalByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateSucursalPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateSucursalCommand;
import com.jcaa.usersmanagement.application.service.mapper.SucursalApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.SucursalNotFoundException;
import com.jcaa.usersmanagement.domain.model.SucursalModel;
import com.jcaa.usersmanagement.domain.valueobject.SucursaId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class UpdateSucursalService implements UpdateSucursalUseCase {

    private final UpdateSucursalPort updateSucursalPort;
    private final GetSucursalByIdPort getSucursalByIdPort;
    private final Validator validator;

    @Override
    public SucursalModel execute(final UpdateSucursalCommand command) {
        validateCommand(command);

        final SucursaId sucursalId = new SucursaId(command.id());
        findExistingSucursalOrFail(sucursalId);

        final SucursalModel sucursalToUpdate = SucursalApplicationMapper.fromUpdateCommandToModel(command);
        return updateSucursalPort.update(sucursalToUpdate);
    }

    private void validateCommand(final UpdateSucursalCommand command) {
        final Set<ConstraintViolation<UpdateSucursalCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private SucursalModel findExistingSucursalOrFail(final SucursaId sucursalId) {
        return getSucursalByIdPort
                .getById(sucursalId)
                .orElseThrow(() -> SucursalNotFoundException.becauseIdWasNotFound(sucursalId.value()));
    }
}