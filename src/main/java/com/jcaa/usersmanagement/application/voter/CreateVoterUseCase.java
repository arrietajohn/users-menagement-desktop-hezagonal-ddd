package com.jcaa.usersmanagement.application.voter;

import com.jcaa.usersmanagement.domain.voter.Voter;

public class CreateVoterUseCase {

    private final VoterRepository repository;

    public CreateVoterUseCase(VoterRepository repository) {
        this.repository = repository;
    }

    public void execute(String dni, String fullName, String email, String commune) {
        Voter voter = new Voter(0, dni, fullName, email, commune);
        repository.save(voter);
    }
}
