package edu.udec.ConcesionarioDeAuto.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.UserModel;
import edu.udec.ConcesionarioDeAuto.domain.model.Cliente;
import edu.udec.ConcesionarioDeAuto.domain.valueobject.Cliente.*;
import edu.udec.ConcesionarioDeAuto.infrastructure.adapter.persistence.dto.PersistenciaClienteDto;
import edu.udec.ConcesionarioDeAuto.infrastructure.adapter.persistence.entity.EntidadCliente;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class PersistenciaClienteMapper {

    public PersistenciaClienteDto fromModelToDto(final Cliente cliente) {
        return new PersistenciaClienteDto(
                cliente.getId().value(),
                cliente.getNombre().value(),
                null, // Segundo nombre por defecto null en infraestructura
                cliente.getApellido().value(),
                null, // Segundo apellido por defecto null en infraestructura
                cliente.getDireccion().value(),
                cliente.getTelefono().value(),
                null,
                null);
    }

    public EntidadCliente fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
        return new EntidadCliente(
                resultSet.getString("id_cliente"), // Fiel a tu base de datos
                resultSet.getString("primerNombre"),
                resultSet.getString("segundoNombre"),
                resultSet.getString("primerApellido"),
                resultSet.getString("segundoApellido"),
                resultSet.getString("direccion"),
                resultSet.getString("telefono"),
                resultSet.getString("created_at"),
                resultSet.getString("updated_at"));
    }

    public Cliente fromEntityTo(final EntidadCliente entidad) {
        // Corregido: Se eliminó el "Cliente cliente" duplicado y se usaron los métodos correctos del record
        return new Cliente(
                new ClienteId(entidad.id_cliente()), // El record de Java usa camelCase en sus métodos
                new ClienteNombre(entidad.primerNombre()),
                new ClienteApellido(entidad.primerApellido()),
                new ClienteDireccion(entidad.direccion()),
                new ClienteTelefono(entidad.telefono())); // Paréntesis cerrado correctamente
    }

    public Cliente fromResultSetTo(final ResultSet resultSet) throws SQLException {
        return fromEntityTo(fromResultSetToEntity(resultSet));
    }

    public List<Cliente> fromResultSetToList(final ResultSet resultSet) throws SQLException {
        final List<Cliente> clientes = new ArrayList<>(); // Corregido nombre de variable duplicado con el tipo
        while (resultSet.next()) {
            clientes.add(fromResultSetTo(resultSet));
        }
        return clientes;
    }


}

