package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateSucursalUseCase;
import com.jcaa.usersmanagement.application.port.out.GetSucursalByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveSucursalPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateSucursalCommand;
import com.jcaa.usersmanagement.application.service.mapper.SucursalApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.SucursalAlreadyExistsException;
import com.jcaa.usersmanagement.domain.model.SucursalModel;
import com.jcaa.usersmanagement.domain.valueobject.SucursaId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.Set;

@Log
@RequiredArgsConstructor
public final class CreateSucursalService implements CreateSucursalUseCase {

    private final SaveSucursalPort saveSucursalPort;
    private final GetSucursalByIdPort getSucursalByIdPort;
    private final Validator validator;

    @Override
    public SucursalModel execute(final CreateSucursalCommand command) {
        validateCommand(command);

        final SucursaId id = new SucursaId(command.id());
        ensureIdIsNotTaken(id);

        final SucursalModel sucursalToSave = SucursalApplicationMapper.fromCreateCommandToModel(command);
        return saveSucursalPort.save(sucursalToSave);
    }

    private void validateCommand(final CreateSucursalCommand command) {
        final Set<ConstraintViolation<CreateSucursalCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void ensureIdIsNotTaken(final SucursaId id) {
        getSucursalByIdPort
                .getById(id)
                .ifPresent(
                        ignored -> {
                            throw SucursalAlreadyExistsException.becauseSucursalAlreadyExists(id.value());
                        });
    }
}