package com.jcaa.usersmanagement.application.voter;

import com.jcaa.usersmanagement.domain.voter.Voter;
import com.jcaa.usersmanagement.domain.voter.VoterNotFoundException;

public class UpdateVoterUseCase {

    private final VoterRepository repository;

    public UpdateVoterUseCase(VoterRepository repository) {
        this.repository = repository;
    }

    public void execute(String dni, String fullName, String email, String commune) {
        Voter existing = repository.findByDni(dni)
                .orElseThrow(() -> new VoterNotFoundException(dni));

        Voter updated = new Voter(existing.getId(), dni, fullName, email, commune);
        repository.update(updated);
    }
}