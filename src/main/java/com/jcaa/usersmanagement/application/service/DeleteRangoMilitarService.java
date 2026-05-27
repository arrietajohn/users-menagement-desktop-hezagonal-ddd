package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteRangoMilitarUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteRangoMilitarPort;
import com.jcaa.usersmanagement.application.port.out.GetRangoMilitarByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteRangoMilitarCommand;
import com.jcaa.usersmanagement.domain.exception.RangoMilitarNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.RangoId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import java.util.Set;

@RequiredArgsConstructor
public final class DeleteRangoMilitarService implements DeleteRangoMilitarUseCase {

    private final DeleteRangoMilitarPort deleteRangoMilitarPort;
    private final GetRangoMilitarByIdPort getRangoMilitarByIdPort;
    private final Validator validator;

    @Override
    public void execute(final DeleteRangoMilitarCommand command) {
        validateCommand(command);
        final RangoId id = new RangoId(command.id());
        ensureRangoExists(id);
        deleteRangoMilitarPort.delete(id);
    }

    private void validateCommand(final DeleteRangoMilitarCommand command) {
        final Set<ConstraintViolation<DeleteRangoMilitarCommand>> violations =
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
