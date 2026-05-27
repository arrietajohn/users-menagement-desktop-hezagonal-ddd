package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateRangoMilitarUseCase;
import com.jcaa.usersmanagement.application.port.out.GetRangoMilitarByCodigoPort;
import com.jcaa.usersmanagement.application.port.out.SaveRangoMilitarPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateRangoMilitarCommand;
import com.jcaa.usersmanagement.application.service.mapper.RangoMilitarApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.RangoMilitarAlreadyExistsException;
import com.jcaa.usersmanagement.domain.model.RangoMilitarModel;
import com.jcaa.usersmanagement.domain.valueobject.RangoCodigo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import java.util.Set;

@RequiredArgsConstructor
public final class CreateRangoMilitarService implements CreateRangoMilitarUseCase {

    private final SaveRangoMilitarPort saveRangoMilitarPort;
    private final GetRangoMilitarByCodigoPort getRangoMilitarByCodigoPort;
    private final Validator validator;

    @Override
    public RangoMilitarModel execute(final CreateRangoMilitarCommand command) {
        validateCommand(command);
        ensureCodigoIsNotTaken(new RangoCodigo(command.codigo()));
        final RangoMilitarModel rangoToSave =
                RangoMilitarApplicationMapper.fromCreateCommandToModel(command);
        return saveRangoMilitarPort.save(rangoToSave);
    }

    private void validateCommand(final CreateRangoMilitarCommand command) {
        final Set<ConstraintViolation<CreateRangoMilitarCommand>> violations =
                validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void ensureCodigoIsNotTaken(final RangoCodigo codigo) {
        getRangoMilitarByCodigoPort
                .getByCodigo(codigo)
                .ifPresent(ignored -> {
                    throw RangoMilitarAlreadyExistsException.becauseCodigoAlreadyExists(codigo.value());
                });
    }
}
