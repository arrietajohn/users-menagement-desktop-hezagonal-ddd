package com.jcaa.usersmanagement.application.candidate;

import com.jcaa.usersmanagement.domain.candidate.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository {

    void save(Candidate candidate);

    Optional<Candidate> findById(String id);

    List<Candidate> findAll();

    void deleteById(String id);
}