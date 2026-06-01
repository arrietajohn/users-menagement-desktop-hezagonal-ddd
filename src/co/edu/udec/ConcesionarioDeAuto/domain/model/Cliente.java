package edu.udec.ConcesionarioDeAuto.domain.model;

import edu.udec.ConcesionarioDeAuto.domain.valueobject.Cliente.*;



import lombok.Value;

@Value
public class Cliente {

    ClienteId id;
    ClienteNombre nombre;
    ClienteApellido apellido;
    ClienteDireccion direccion;
    ClienteTelefono telefono;

    public static Cliente crear(
            final ClienteId id,
            final ClienteNombre nombre,
            final ClienteApellido apellido,
            final ClienteDireccion direccion,
            final ClienteTelefono telefono
    ) {
        return new Cliente(id, nombre, apellido, direccion, telefono);
    }
}
