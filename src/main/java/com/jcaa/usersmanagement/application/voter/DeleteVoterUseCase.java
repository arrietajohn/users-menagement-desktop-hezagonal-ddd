package com.jcaa.usersmanagement.application.voter;

import com.jcaa.usersmanagement.domain.voter.VoterNotFoundException;

public class DeleteVoterUseCase {

    private final VoterRepository repository;

    public DeleteVoterUseCase(VoterRepository repository) {
        this.repository = repository;
    }

    public void execute(String dni) {
        repository.findByDni(dni)
                .orElseThrow(() -> new VoterNotFoundException(dni));
        repository.deleteByDni(dni);
    }
}