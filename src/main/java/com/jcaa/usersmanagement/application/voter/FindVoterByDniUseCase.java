package com.jcaa.usersmanagement.application.voter;

import com.jcaa.usersmanagement.domain.voter.Voter;
import com.jcaa.usersmanagement.domain.voter.VoterNotFoundException;

public class FindVoterByDniUseCase {

    private final VoterRepository repository;

    public FindVoterByDniUseCase(VoterRepository repository) {
        this.repository = repository;
    }

    public Voter execute(String dni) {
        return repository.findByDni(dni)
                .orElseThrow(() -> new VoterNotFoundException(dni));
    }
}