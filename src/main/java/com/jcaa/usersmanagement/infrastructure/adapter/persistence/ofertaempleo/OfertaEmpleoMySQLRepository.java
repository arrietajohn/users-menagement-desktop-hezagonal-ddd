package com.jcaa.usersmanagement.infrastructure.adapter.persistence.ofertaempleo;

import com.jcaa.usersmanagement.application.port.out.ofertaempleo.OfertaEmpleoRepositoryPort;
import com.jcaa.usersmanagement.domain.model.OfertaEmpleoModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.OfertaEmpleoEntity;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.OfertaEmpleoPersistenceMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OfertaEmpleoMySQLRepository implements OfertaEmpleoRepositoryPort {

    private final Connection connection;

    public OfertaEmpleoMySQLRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(OfertaEmpleoModel oferta) {
        String sql = "INSERT INTO ofertas_empleo VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            OfertaEmpleoEntity entity =
                    OfertaEmpleoPersistenceMapper.fromModelToEntity(oferta);

            ps.setString(1, entity.id());
            ps.setString(2, entity.titulo());
            ps.setString(3, entity.descripcion());
            ps.setString(4, entity.empresa());
            ps.setString(5, entity.ubicacion());
            ps.setBigDecimal(6, entity.salario());
            ps.setString(7, entity.estado());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<OfertaEmpleoModel> findById(String id) {
        String sql = "SELECT * FROM ofertas_empleo WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(
                        OfertaEmpleoPersistenceMapper.fromResultSetToModel(rs)
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public List<OfertaEmpleoModel> findAll() {
        String sql = "SELECT * FROM ofertas_empleo";
        List<OfertaEmpleoModel> list = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            return OfertaEmpleoPersistenceMapper.fromResultSetToList(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(OfertaEmpleoModel oferta) {
        String sql = """
            UPDATE ofertas_empleo
            SET titulo=?, descripcion=?, empresa=?, ubicacion=?, salario=?, estado=?
            WHERE id=?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            OfertaEmpleoEntity entity =
                    OfertaEmpleoPersistenceMapper.fromModelToEntity(oferta);

            ps.setString(1, entity.titulo());
            ps.setString(2, entity.descripcion());
            ps.setString(3, entity.empresa());
            ps.setString(4, entity.ubicacion());
            ps.setBigDecimal(5, entity.salario());
            ps.setString(6, entity.estado());
            ps.setString(7, entity.id());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM ofertas_empleo WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}