package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteAerolineaPort;
import com.jcaa.usersmanagement.application.port.out.GetAllAerolineaPort;
import com.jcaa.usersmanagement.application.port.out.SaveAerolineaPort;
import com.jcaa.usersmanagement.domain.model.AerolineaModel;
import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class AerolineaRepositoryMySQL
        implements SaveAerolineaPort,
        GetAllAerolineaPort,
        DeleteAerolineaPort {

    private final Connection connection;

    private static final String SQL_INSERT =
            "INSERT INTO aerolinea(nombre, pais_origen) VALUES (?, ?)";

    private static final String SQL_SELECT_ALL =
            "SELECT id_aerolinea, nombre, pais_origen FROM aerolinea";

    private static final String SQL_DELETE =
            "DELETE FROM aerolinea WHERE id_aerolinea = ?";

    @Override
    public AerolineaModel save(AerolineaModel aerolinea) {

        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_INSERT)) {

            statement.setString(1, aerolinea.getNombre());
            statement.setString(2, aerolinea.getPaisOrigen());

            statement.executeUpdate();

            return aerolinea;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AerolineaModel> getAll() {

        List<AerolineaModel> lista = new ArrayList<>();

        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_ALL)) {

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                lista.add(
                        new AerolineaModel(
                                rs.getInt("id_aerolinea"),
                                rs.getString("nombre"),
                                rs.getString("pais_origen")
                        )
                );
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {

        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_DELETE)) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}