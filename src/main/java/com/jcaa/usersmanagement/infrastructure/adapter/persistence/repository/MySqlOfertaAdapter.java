package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.OfertaRepositoryPort;
import com.jcaa.usersmanagement.domain.model.Oferta;
import com.jcaa.usersmanagement.infrastructure.config.DatabaseConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlOfertaAdapter implements OfertaRepositoryPort {
    @Override
    public void guardar(Oferta oferta) {
        String query = "INSERT INTO ofertas (id_subasta, id_usuario_ofertante, monto, fecha_hora) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, oferta.getIdSubasta());
            ps.setInt(2, oferta.getIdUsuarioOfertante());
            ps.setBigDecimal(3, oferta.getMonto());
            ps.setTimestamp(4, Timestamp.valueOf(oferta.getFechaHora()));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) oferta.setIdOferta(rs.getInt(1));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public List<Oferta> buscarPorSubasta(Integer idSubasta) {
        List<Oferta> lista = new ArrayList<>();
        String query = "SELECT * FROM ofertas WHERE id_subasta = ? ORDER BY monto DESC";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idSubasta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapRow(rs));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return lista;
    }

    @Override
    public Optional<Oferta> buscarPorId(Integer id) {
        String query = "SELECT * FROM ofertas WHERE id_oferta = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return Optional.empty();
    }

    private Oferta mapRow(ResultSet rs) throws SQLException {
        Oferta o = new Oferta();
        o.setIdOferta(rs.getInt("id_oferta"));
        o.setIdSubasta(rs.getInt("id_subasta"));
        o.setIdUsuarioOfertante(rs.getInt("id_usuario_ofertante"));
        o.setMonto(rs.getBigDecimal("monto"));
        o.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());
        return o;
    }
}