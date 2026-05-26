package com.jcaa.usersmanagement.application.service.nino;

import com.jcaa.usersmanagement.application.port.in.ListNinosUseCase;
import com.jcaa.usersmanagement.application.service.nino.dto.NinoResponse;
import com.jcaa.usersmanagement.domain.model.nino.Nino;
import com.jcaa.usersmanagement.domain.model.nino.NinoRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ListNinosUseCaseImpl implements ListNinosUseCase {

    private final NinoRepository ninoRepository;

    public ListNinosUseCaseImpl(NinoRepository ninoRepository) {
        this.ninoRepository = ninoRepository;
    }

    @Override
    public List<NinoResponse> execute() {
        List<Nino> ninos = ninoRepository.findAll();

        return ninos.stream()
                .map(NinoResponse::new)
                .collect(Collectors.toList());
    }
}