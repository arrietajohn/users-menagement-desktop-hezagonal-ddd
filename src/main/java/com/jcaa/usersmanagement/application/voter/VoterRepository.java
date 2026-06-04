package com.jcaa.usersmanagement.application.voter;

import com.jcaa.usersmanagement.domain.voter.Voter;
import java.util.List;
import java.util.Optional;

public interface VoterRepository {
    void save(Voter voter);
    Optional<Voter> findByDni(String dni);
    List<Voter> findAll();
    void update(Voter voter);
    void deleteByDni(String dni);
}