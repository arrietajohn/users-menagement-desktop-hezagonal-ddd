package com.jcaa.usersmanagement.application.service.nino;

import com.jcaa.usersmanagement.application.port.in.GetNinoByIdUseCase;
import com.jcaa.usersmanagement.application.service.nino.dto.NinoResponse;
import com.jcaa.usersmanagement.domain.model.nino.NinoNotFoundException;
import com.jcaa.usersmanagement.domain.model.nino.NinoRepository;

public class GetNinoByIdUseCaseImpl implements GetNinoByIdUseCase {

    private final NinoRepository ninoRepository;

    public GetNinoByIdUseCaseImpl(NinoRepository ninoRepository) {
        this.ninoRepository = ninoRepository;
    }

    @Override
    public NinoResponse execute(Long id) {
        var nino = ninoRepository.findById(id)
                .orElseThrow(() -> new NinoNotFoundException(id));

        return new NinoResponse(nino);
    }
}