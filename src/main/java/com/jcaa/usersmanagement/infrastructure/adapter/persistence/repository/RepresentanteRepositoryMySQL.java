package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteRepresentantePort;
import com.jcaa.usersmanagement.application.port.out.GetAllRepresentantesPort;
import com.jcaa.usersmanagement.application.port.out.GetRepresentanteByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveRepresentantePort;
import com.jcaa.usersmanagement.application.port.out.UpdateRepresentantePort;
import com.jcaa.usersmanagement.domain.model.RepresentanteModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.RepresentantePersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.RepresentantePersistenceMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepresentanteRepositoryMySQL implements SaveRepresentantePort, GetAllRepresentantesPort,
        GetRepresentanteByIdPort, UpdateRepresentantePort, DeleteRepresentantePort {

    private final Connection connection;
    private final RepresentantePersistenceMapper mapper;

    public RepresentanteRepositoryMySQL(Connection connection) {
        this.connection = connection;
        this.mapper = new RepresentantePersistenceMapper();
    }

    @Override
    public RepresentanteModel save(RepresentanteModel representante) {
        String sql = "INSERT INTO representante (id_representante, nombre, telefono, direccion) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, representante.getId());
            stmt.setString(2, representante.getNombre());
            stmt.setString(3, representante.getTelefono());
            stmt.setString(4, representante.getDireccion());
            stmt.executeUpdate();
            return representante;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar representante: " + e.getMessage());
        }
    }

    @Override
    public List<RepresentanteModel> findAll() {
        String sql = "SELECT * FROM representante";
        List<RepresentanteModel> representantes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                representantes.add(mapper.toDomain(toDto(rs)));
            }
            return representantes;
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar representantes: " + e.getMessage());
        }
    }

    @Override
    public Optional<RepresentanteModel> findById(Integer id) {
        String sql = "SELECT * FROM representante WHERE id_representante = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapper.toDomain(toDto(rs)));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar representante: " + e.getMessage());
        }
    }

    @Override
    public RepresentanteModel update(RepresentanteModel representante) {
        String sql = "UPDATE representante SET nombre=?, telefono=?, direccion=? WHERE id_representante=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, representante.getNombre());
            stmt.setString(2, representante.getTelefono());
            stmt.setString(3, representante.getDireccion());
            stmt.setInt(4, representante.getId());
            stmt.executeUpdate();
            return representante;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar representante: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM representante WHERE id_representante = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar representante: " + e.getMessage());
        }
    }

    private RepresentantePersistenceDto toDto(ResultSet rs) throws SQLException {
        return new RepresentantePersistenceDto(
                rs.getInt("id_representante"),
                rs.getString("nombre"),
                rs.getString("telefono"),
                rs.getString("direccion")
        );
    }
}