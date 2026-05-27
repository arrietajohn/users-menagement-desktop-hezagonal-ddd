package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.*;
import com.jcaa.usersmanagement.domain.model.nino.Nino;
import com.jcaa.usersmanagement.domain.model.nino.NinoNotFoundException;
import com.jcaa.usersmanagement.domain.model.nino.vo.Matricula;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log
@RequiredArgsConstructor
public final class NinoRepositoryMySQL
        implements SaveNinoPort, GetNinoByIdPort, GetAllNinosPort, DeleteNinoPort, UpdateNinoPort {

    private final Connection connection;

    @Override
    public Nino save(Nino nino) {
        String sql = """
            INSERT INTO ninos (numero_matricula, nombre_completo, fecha_nacimiento, 
                             fecha_ingreso, estado, created_at, updated_at)
            VALUES (?, ?, ?, ?, 'ACTIVO', NOW(), NOW())
            """;

        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, nino.getMatricula().getValue());
            stmt.setString(2, nino.getNombreCompleto());
            stmt.setObject(3, nino.getFechaNacimiento());
            stmt.setObject(4, nino.getFechaIngreso());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    Long generatedId = rs.getLong(1);
                    return new Nino(
                            generatedId,
                            nino.getMatricula(),
                            nino.getNombreCompleto(),
                            nino.getFechaNacimiento(),
                            nino.getFechaIngreso()
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar niño", e);
        }

        return nino;
    }

    @Override
    public Optional<Nino> getById(Long id) {
        String sql = """
            SELECT id, numero_matricula, nombre_completo, fecha_nacimiento, 
                   fecha_ingreso, fecha_baja, estado, created_at, updated_at
            FROM ninos 
            WHERE id = ? LIMIT 1
            """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Nino(
                        rs.getLong("id"),
                        new Matricula(rs.getString("numero_matricula")),
                        rs.getString("nombre_completo"),
                        rs.getObject("fecha_nacimiento", LocalDate.class),
                        rs.getObject("fecha_ingreso", LocalDate.class)
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar niño por ID", e);
        }
    }

    @Override
    public List<Nino> getAll() {
        String sql = """
            SELECT id, numero_matricula, nombre_completo, fecha_nacimiento, 
                   fecha_ingreso, fecha_baja, estado, created_at, updated_at
            FROM ninos 
            ORDER BY nombre_completo ASC
            """;

        List<Nino> ninos = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ninos.add(new Nino(
                        rs.getLong("id"),
                        new Matricula(rs.getString("numero_matricula")),
                        rs.getString("nombre_completo"),
                        rs.getObject("fecha_nacimiento", LocalDate.class),
                        rs.getObject("fecha_ingreso", LocalDate.class)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar niños", e);
        }

        return ninos;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM ninos WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar niño", e);
        }
    }

    @Override
    public Nino update(Nino nino) {
        String sql = """
            UPDATE ninos 
            SET nombre_completo = ?, fecha_nacimiento = ?, 
                fecha_ingreso = ?, updated_at = NOW()
            WHERE id = ?
            """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nino.getNombreCompleto());
            stmt.setObject(2, nino.getFechaNacimiento());
            stmt.setObject(3, nino.getFechaIngreso());
            stmt.setLong(4, nino.getId());

            stmt.executeUpdate();
            return nino;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar niño", e);
        }
    }
}