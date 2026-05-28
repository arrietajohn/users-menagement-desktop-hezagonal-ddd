package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.*;
import com.jcaa.usersmanagement.domain.model.nino.Nino;
import com.jcaa.usersmanagement.domain.model.nino.NinoNotFoundException;
import com.jcaa.usersmanagement.domain.model.nino.vo.Matricula;
import com.jcaa.usersmanagement.domain.model.nino.NinoRepository;
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
        implements NinoRepository,
        SaveNinoPort,
        GetNinoByIdPort,
        GetAllNinosPort,
        DeleteNinoPort,
        UpdateNinoPort {


    private final Connection connection;

    @Override
    public Nino save(Nino nino) {
        String sql = """
            INSERT INTO nino (numero_matricula, nombre_completo, fecha_nacimiento, 
                             fecha_ingreso, estado)
            VALUES (?, ?, ?, ?, 'ACTIVO')
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
                    System.out.println("✅ ID generado por BD: " + generatedId);

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
            e.printStackTrace();
            throw new RuntimeException("Error al guardar niño: " + e.getMessage(), e);
        }

        return nino;
    }

    @Override
    public Optional<Nino> getById(Long id) {
        String sql = """
            SELECT numero_matricula, nombre_completo, fecha_nacimiento, 
                   fecha_ingreso, fecha_baja, estado
            FROM nino 
            WHERE numero_matricula = ? LIMIT 1
            """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id.toString());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Nino(
                        null,  // ID numérico no lo tenemos
                        new Matricula(rs.getString("numero_matricula")),
                        rs.getString("nombre_completo"),
                        rs.getObject("fecha_nacimiento", LocalDate.class),
                        rs.getObject("fecha_ingreso", LocalDate.class)
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar niño por ID: " + e.getMessage(), e);
        }
    }
    @Override
    public List<Nino> getAll() {
        String sql = """
            SELECT numero_matricula, nombre_completo, fecha_nacimiento, 
                   fecha_ingreso, fecha_baja, estado
            FROM nino 
            ORDER BY nombre_completo ASC
            """;

        List<Nino> ninos = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ninos.add(new Nino(
                        null,
                        new Matricula(rs.getString("numero_matricula")),
                        rs.getString("nombre_completo"),
                        rs.getObject("fecha_nacimiento", LocalDate.class),
                        rs.getObject("fecha_ingreso", LocalDate.class)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al listar niños: " + e.getMessage(), e);
        }

        return ninos;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM nino WHERE numero_matricula = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar niño: " + e.getMessage(), e);
        }
    }

    @Override
    public Nino update(Nino nino) {
        String sql = """
            UPDATE nino 
            SET nombre_completo = ?, 
                fecha_nacimiento = ?, 
                fecha_ingreso = ?
            WHERE numero_matricula = ?
            """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nino.getNombreCompleto());
            stmt.setObject(2, nino.getFechaNacimiento());
            stmt.setObject(3, nino.getFechaIngreso());
            stmt.setString(4, nino.getMatricula().getValue());

            stmt.executeUpdate();
            return nino;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar niño: " + e.getMessage(), e);
        }
    }
    @Override
    public Optional<Nino> findByMatricula(Matricula matricula) {
        return Optional.empty();
    }

    @Override
    public List<Nino> findActivos() {
        return getAll();
    }

    @Override
    public boolean existsByMatricula(Matricula matricula) {
        return false;
    }
    @Override
    public Optional<Nino> findById(Long id) {
        return getById(id);
    }

    @Override
    public List<Nino> findAll() {
        return getAll();
    }
}