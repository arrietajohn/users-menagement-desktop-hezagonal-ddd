package com.jcaa.usersmanagement.application.candidate;

import com.jcaa.usersmanagement.domain.candidate.Candidate;

import java.util.UUID;

public class CreateCandidateUseCase {

    private final CandidateRepository repository;

    public CreateCandidateUseCase(CandidateRepository repository) {
        this.repository = repository;
    }

    public void execute(String dni, String name, String party) {

        String id = UUID.randomUUID().toString();

        Candidate candidate = new Candidate(id, dni, name, party);

        repository.save(candidate);
    }
}