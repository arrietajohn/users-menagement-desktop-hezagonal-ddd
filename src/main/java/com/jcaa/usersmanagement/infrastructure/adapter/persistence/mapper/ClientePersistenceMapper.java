package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.ClienteModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.ClientePersistenceDto;
import lombok.experimental.UtilityClass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ClientePersistenceMapper {

    public ClientePersistenceDto fromModelToDto(final ClienteModel cliente) {
        return new ClientePersistenceDto(
                cliente.getIdCliente(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getEmail(),
                cliente.getTelefono(),
                cliente.getDireccion()
        );
    }

    public ClienteModel fromResultSetToModel(final ResultSet rs) throws SQLException {
        return new ClienteModel(
                rs.getInt("id_cliente"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("email"),
                rs.getString("telefono"),
                rs.getString("direccion")
        );
    }

    public List<ClienteModel> fromResultSetToModelList(final ResultSet rs) throws SQLException {
        final List<ClienteModel> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(fromResultSetToModel(rs));
        }
        return lista;
    }
}
