package com.jcaa.usersmanagement.infrastructure.trabajogrado;

import com.jcaa.usersmanagement.application.trabajogrado.TrabajoGradoRepository;
import com.jcaa.usersmanagement.domain.model.trabajogrado.TrabajoGrado;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrabajoGradoRepositoryImpl implements TrabajoGradoRepository {

    private final Connection connection;

    public TrabajoGradoRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(TrabajoGrado tg) {
        String sql = "INSERT INTO trabajos_grado (numero_orden, tema, fecha_inicio, alumno_matricula) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, tg.getNumeroOrden());
            ps.setString(2, tg.getTema());
            ps.setDate(3, Date.valueOf(tg.getFechaInicio()));
            ps.setString(4, tg.getAlumnoMatricula());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar trabajo de grado: " + e.getMessage());
        }
    }

    @Override
    public Optional<TrabajoGrado> findByNumeroOrden(Integer numeroOrden) {
        String sql = "SELECT * FROM trabajos_grado WHERE numero_orden = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, numeroOrden);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar trabajo de grado: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<TrabajoGrado> findAll() {
        List<TrabajoGrado> lista = new ArrayList<>();
        String sql = "SELECT * FROM trabajos_grado";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar trabajos de grado: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void update(TrabajoGrado tg) {
        String sql = "UPDATE trabajos_grado SET tema=?, fecha_inicio=?, alumno_matricula=? WHERE numero_orden=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tg.getTema());
            ps.setDate(2, Date.valueOf(tg.getFechaInicio()));
            ps.setString(3, tg.getAlumnoMatricula());
            ps.setInt(4, tg.getNumeroOrden());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar trabajo de grado: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer numeroOrden) {
        String sql = "DELETE FROM trabajos_grado WHERE numero_orden=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, numeroOrden);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar trabajo de grado: " + e.getMessage());
        }
    }

    @Override
    public boolean existsByNumeroOrden(Integer numeroOrden) {
        String sql = "SELECT COUNT(*) FROM trabajos_grado WHERE numero_orden=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, numeroOrden);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar trabajo de grado: " + e.getMessage());
        }
        return false;
    }

    private TrabajoGrado mapRow(ResultSet rs) throws SQLException {
        return TrabajoGrado.builder()
                .numeroOrden(rs.getInt("numero_orden"))
                .tema(rs.getString("tema"))
                .fechaInicio(rs.getDate("fecha_inicio").toLocalDate())
                .alumnoMatricula(rs.getString("alumno_matricula"))
                .build();
    }
}