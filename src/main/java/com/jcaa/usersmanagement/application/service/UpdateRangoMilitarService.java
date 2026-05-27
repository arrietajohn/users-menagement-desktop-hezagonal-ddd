package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateRangoMilitarUseCase;
import com.jcaa.usersmanagement.application.port.out.GetRangoMilitarByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateRangoMilitarPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateRangoMilitarCommand;
import com.jcaa.usersmanagement.application.service.mapper.RangoMilitarApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.RangoMilitarNotFoundException;
import com.jcaa.usersmanagement.domain.model.RangoMilitarModel;
import com.jcaa.usersmanagement.domain.valueobject.RangoId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import java.util.Set;

@RequiredArgsConstructor
public final class UpdateRangoMilitarService implements UpdateRangoMilitarUseCase {

    private final UpdateRangoMilitarPort updateRangoMilitarPort;
    private final GetRangoMilitarByIdPort getRangoMilitarByIdPort;
    private final Validator validator;

    @Override
    public RangoMilitarModel execute(final UpdateRangoMilitarCommand command) {
        validateCommand(command);
        ensureRangoExists(new RangoId(command.id()));
        final RangoMilitarModel rangoToUpdate =
                RangoMilitarApplicationMapper.fromUpdateCommandToModel(command);
        return updateRangoMilitarPort.update(rangoToUpdate);
    }

    private void validateCommand(final UpdateRangoMilitarCommand command) {
        final Set<ConstraintViolation<UpdateRangoMilitarCommand>> violations =
                validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void ensureRangoExists(final RangoId id) {
        getRangoMilitarByIdPort
                .getById(id)
                .orElseThrow(() -> RangoMilitarNotFoundException.becauseIdWasNotFound(id.value()));
    }
}
