package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateAerolineaUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveAerolineaPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateAerolineaCommand;
import com.jcaa.usersmanagement.domain.model.AerolineaModel;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import jakarta.validation.ConstraintViolation;

@RequiredArgsConstructor
public class CreateAerolineaService implements CreateAerolineaUseCase {

    private final SaveAerolineaPort saveAerolineaPort;
    private final Validator validator;

    @Override
    public AerolineaModel execute(final CreateAerolineaCommand command) {
        validateCommand(command);

        AerolineaModel model = new AerolineaModel(
                command.idAerolinea(),
                command.nombre(),
                command.paisOrigen()
        );

        return saveAerolineaPort.save(model);
    }

    private void validateCommand(final CreateAerolineaCommand command) {
        final Set<ConstraintViolation<CreateAerolineaCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}