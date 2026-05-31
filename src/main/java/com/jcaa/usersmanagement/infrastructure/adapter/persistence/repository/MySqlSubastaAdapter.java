package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.SubastaRepositoryPort;
import com.jcaa.usersmanagement.domain.model.Subasta;
import com.jcaa.usersmanagement.infrastructure.config.DatabaseConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlSubastaAdapter implements SubastaRepositoryPort {
    @Override
    public void guardar(Subasta subasta) {
        String query = "INSERT INTO subastas (id_articulo, precio_inicial, precio_actual, fecha_inicio, fecha_limite, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, subasta.getIdArticulo());
            ps.setBigDecimal(2, subasta.getPrecioInicial());
            ps.setBigDecimal(3, subasta.getPrecioActual());
            ps.setTimestamp(4, Timestamp.valueOf(subasta.getFechaInicio()));
            ps.setTimestamp(5, Timestamp.valueOf(subasta.getFechaLimite()));
            ps.setString(6, subasta.getEstado());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) subasta.setIdSubasta(rs.getInt(1));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public Optional<Subasta> buscarPorId(Integer id) {
        String query = "SELECT * FROM subastas WHERE id_subasta = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return Optional.empty();
    }

    @Override
    public List<Subasta> buscarTodas() {
        List<Subasta> lista = new ArrayList<>();
        String query = "SELECT * FROM subastas";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapRow(rs));
        } catch (SQLException e) { throw new RuntimeException(e); }
        return lista;
    }

    @Override
    public void actualizar(Subasta subasta) {
        String query = "UPDATE subastas SET precio_actual = ?, estado = ? WHERE id_subasta = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setBigDecimal(1, subasta.getPrecioActual());
            ps.setString(2, subasta.getEstado());
            ps.setInt(3, subasta.getIdSubasta());
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public void eliminar(Integer id) {
        String query = "DELETE FROM subastas WHERE id_subasta = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    private Subasta mapRow(ResultSet rs) throws SQLException {
        Subasta s = new Subasta();
        s.setIdSubasta(rs.getInt("id_subasta"));
        s.setIdArticulo(rs.getInt("id_articulo"));
        s.setPrecioInicial(rs.getBigDecimal("precio_inicial"));
        s.setPrecioActual(rs.getBigDecimal("precio_actual"));
        s.setFechaInicio(rs.getTimestamp("fecha_inicio").toLocalDateTime());
        s.setFechaLimite(rs.getTimestamp("fecha_limite").toLocalDateTime());
        s.setEstado(rs.getString("estado"));
        return s;
    }
}