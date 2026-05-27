package com.jcaa.usersmanagement.application.service.nino;

import com.jcaa.usersmanagement.application.port.in.UpdateNinoUseCase;
import com.jcaa.usersmanagement.application.service.nino.dto.NinoResponse;
import com.jcaa.usersmanagement.domain.model.nino.Nino;
import com.jcaa.usersmanagement.domain.model.nino.NinoNotFoundException;
import com.jcaa.usersmanagement.domain.model.nino.NinoRepository;

import java.time.LocalDate;

public class UpdateNinoService implements UpdateNinoUseCase {

    private final NinoRepository ninoRepository;

    public UpdateNinoService(NinoRepository ninoRepository) {
        this.ninoRepository = ninoRepository;
    }

    @Override
    public NinoResponse execute(Long id, String nombreCompleto, LocalDate fechaNacimiento) {
        Nino nino = ninoRepository.findById(id)
                .orElseThrow(() -> new NinoNotFoundException(id));


        Nino updatedNino = ninoRepository.save(nino);

        return new NinoResponse(updatedNino);
    }
}
