package com.jcaa.usersmanagement.infrastructure.adapter.persistence.voter;

import com.jcaa.usersmanagement.application.voter.VoterRepository;
import com.jcaa.usersmanagement.domain.voter.Voter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlVoterRepository implements VoterRepository {

    private final Connection connection;

    public MySqlVoterRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Voter voter) {
        try {
            String sql = "INSERT INTO voter (dni, full_name, email, commune) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, voter.getDni());
            statement.setString(2, voter.getFullName());
            statement.setString(3, voter.getEmail());
            statement.setString(4, voter.getCommune());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving voter", e);
        }
    }

    @Override
    public Optional<Voter> findByDni(String dni) {
        try {
            String sql = "SELECT * FROM voter WHERE dni = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, dni);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Voter voter = new Voter(
                        rs.getInt("id"),
                        rs.getString("dni"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("commune")
                );
                return Optional.of(voter);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error finding voter", e);
        }
    }

    @Override
    public List<Voter> findAll() {
        try {
            List<Voter> voters = new ArrayList<>();
            String sql = "SELECT * FROM voter";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                voters.add(new Voter(
                        rs.getInt("id"),
                        rs.getString("dni"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("commune")
                ));
            }
            return voters;
        } catch (SQLException e) {
            throw new RuntimeException("Error listing voters", e);
        }
    }

    @Override
    public void update(Voter voter) {
        try {
            String sql = "UPDATE voter SET full_name = ?, email = ?, commune = ? WHERE dni = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, voter.getFullName());
            statement.setString(2, voter.getEmail());
            statement.setString(3, voter.getCommune());
            statement.setString(4, voter.getDni());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating voter", e);
        }
    }

    @Override
    public void deleteByDni(String dni) {
        try {
            String sql = "DELETE FROM voter WHERE dni = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, dni);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting voter", e);
        }
    }
}
