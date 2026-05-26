package com.jcaa.usersmanagement.application.service.nino;

import com.jcaa.usersmanagement.application.port.in.DeleteNinoUseCase;
import com.jcaa.usersmanagement.domain.model.nino.NinoNotFoundException;
import com.jcaa.usersmanagement.domain.model.nino.NinoRepository;

public class DeleteNinoService implements DeleteNinoUseCase {

    private final NinoRepository ninoRepository;

    public DeleteNinoService(NinoRepository ninoRepository) {
        this.ninoRepository = ninoRepository;
    }

    @Override
    public void execute(Long id) {
        // Verificar que exista antes de eliminar
        if (!ninoRepository.findById(id).isPresent()) {
            throw new NinoNotFoundException(id);
        }

        ninoRepository.delete(id);
    }
}