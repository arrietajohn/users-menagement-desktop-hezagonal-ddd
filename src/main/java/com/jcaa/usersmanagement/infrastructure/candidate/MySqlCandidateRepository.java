package com.jcaa.usersmanagement.infrastructure.candidate;

import com.jcaa.usersmanagement.application.candidate.CandidateRepository;
import com.jcaa.usersmanagement.domain.candidate.Candidate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlCandidateRepository implements CandidateRepository {

    private final Connection connection;

    public MySqlCandidateRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Candidate candidate) {
        try {
            String sql = "INSERT INTO candidate (id, dni, name, party) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, candidate.getId());
            statement.setString(2, candidate.getDni());
            statement.setString(3, candidate.getName());
            statement.setString(4, candidate.getParty());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving candidate", e);
        }
    }

    @Override
    public void deleteById(String id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public java.util.Optional<Candidate> findById(String id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public java.util.List<Candidate> findAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

