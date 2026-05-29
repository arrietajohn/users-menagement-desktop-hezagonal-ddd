package com.jcaa.usersmanagement.application.voter;

import com.jcaa.usersmanagement.domain.voter.Voter;
import java.util.List;

public class ListVotersUseCase {

    private final VoterRepository repository;

    public ListVotersUseCase(VoterRepository repository) {
        this.repository = repository;
    }

    public List<Voter> execute() {
        return repository.findAll();
    }
}